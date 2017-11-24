package info.smartlife360.meramla;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class complaint_description extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaint_description);
        String g=getIntent().getStringExtra("desc");
        final TextView textView7=(TextView)findViewById(R.id.textView7);
        final TextView textView6=(TextView)findViewById(R.id.textView6);
        final TextView date=(TextView)findViewById(R.id.textView8);
        final TextView desc=(TextView)findViewById(R.id.textView9);

        ParseQuery<ParseObject> po=new ParseQuery<ParseObject>("Compliants");
        po.whereEqualTo("uname",g);
        po.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> list, ParseException e) {
                if(e==null)
                {
                    String Complaint_title=list.get(0).getString("compliant_title");
                    String Complaint_desc=list.get(0).getString("compliant_description");
                    String Complaint_status=list.get(0).getString("complaint_status");
                   Date Complaint_date= list.get(0).getCreatedAt();
                    DateFormat df=new SimpleDateFormat("dd-MM-yyy HH:mm:ss");

                    String d=df.format(Complaint_date);
                    textView7.setText(Complaint_title);
                    textView6.setText(Complaint_status);
                    String head="To\nNavjot Singh Sidhu\nMLA West Amritsar\n\n"+Complaint_desc+"\n\nFrom\n"+ParseUser.getCurrentUser().getUsername();
                    desc.setText(head);
                    date.setText(d);
                }
            }
        });
    }
}
