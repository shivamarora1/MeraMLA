package info.smartlife360.meramla;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

public class Leader_main extends AppCompatActivity implements View.OnClickListener {

    Button button5;
    Button button4;
    TextView pend;
    TextView sol;
    TextView rej;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leader_main);
        button5=(Button)findViewById(R.id.button5);
        button4=(Button)findViewById(R.id.button4);
        pend=(TextView)findViewById(R.id.textView27);
        sol=(TextView)findViewById(R.id.textView26);
        rej=(TextView)findViewById(R.id.textView25);

        button5.setOnClickListener(this);
        button4.setOnClickListener(this);

        ParseQuery<ParseObject> po=new ParseQuery<ParseObject>("Compliants");
        po.whereEqualTo("complaint_status","Solved");
        po.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> list, ParseException e) {
                if(e==null){
                    sol.setText("Solved Complaints : "+list.size());
                }
            }
        });

        ParseQuery<ParseObject> p1=new ParseQuery<ParseObject>("Compliants");
        p1.whereEqualTo("complaint_status","Pending");
        p1.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> list, ParseException e) {
                if(e==null){
                    pend.setText("Pending Complaints : "+list.size());
                }
            }
        });

        ParseQuery<ParseObject> p2=new ParseQuery<ParseObject>("Compliants");
        p2.whereEqualTo("complaint_status","Rejected");
        p2.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> list, ParseException e) {
                if(e==null){
                    rej.setText("Rejected Compliants : "+list.size());
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.button4:{
                Intent i=new Intent(getApplicationContext(),Leader_activity.class);
                startActivity(i);
                break;
            }
            case R.id.button5:{
                Intent i=new Intent(getApplicationContext(),leader_board.class);
                startActivity(i);
                break;
            }
        }
    }
}
