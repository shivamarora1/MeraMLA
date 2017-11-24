package info.smartlife360.meramla;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

public class view_status extends AppCompatActivity {

    ListView complaint_status;
    ArrayList<String> complaints=new ArrayList<>();
    ArrayList<String> complaints_id=new ArrayList<>();
    private ArrayAdapter arrayAdapter;
    int i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_status);

        i=1;
        complaint_status=(ListView)findViewById(R.id.complaint_status);
        arrayAdapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1,complaints);
        complaints.add("You have not Submitted any complaint yet");
        complaint_status.setAdapter(arrayAdapter);

        ParseQuery<ParseObject> po=new ParseQuery<>("Compliants");
        po.addDescendingOrder("createdAt");
        po.whereEqualTo("uname", ParseUser.getCurrentUser().getUsername());
        po.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> list, ParseException e) {
                if(e==null)
                {
                    if(list.size()>0){
                        complaints.clear();
                        for(ParseObject p:list)
                        {
                            String g=i+" "+p.getString("compliant_title");
                            complaints.add(g);
                            complaints_id.add(p.getString("uname"));
                            i++;
                        }
                        arrayAdapter.notifyDataSetChanged();
                    }
                    else{
                        complaints.add("You have not Submitted any complaint yet");
                    }
                }
                else
                    {
                        e.printStackTrace();
                }
            }
        });
    complaint_status.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent i=new Intent(getApplicationContext(),complaint_description.class);
            i.putExtra("desc", complaints_id.get(position));
            startActivity(i);
        }
    });
    }
}
