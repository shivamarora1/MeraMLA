package info.smartlife360.meramla;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Leader_complaint_description extends AppCompatActivity  {

    TextView complaint_desc;
    TextView complaint_date;
    Spinner stats;
    Button leader_update;
    List<String> statuses;
    ArrayAdapter ad;
    String user;
    String uCredentials;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leader_complaint_description);

        user = getIntent().getStringExtra("uname");
        complaint_desc = (TextView) findViewById(R.id.textView19);
        complaint_date = (TextView) findViewById(R.id.textView20);
        stats = (Spinner) findViewById(R.id.spinner);
        leader_update = (Button) findViewById(R.id.button);
        statuses = new ArrayList<>();

        leader_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseQuery<ParseObject> po = new ParseQuery<ParseObject>("Compliants");
                po.whereEqualTo("uname", user);
                po.findInBackground(new FindCallback<ParseObject>() {
                    @Override
                    public void done(List<ParseObject> list, ParseException e) {
                        if(e==null && list.size()>0)
                        {
                            String sel=stats.getSelectedItem().toString();
                            list.get(0).put("complaint_status",sel);
                            list.get(0).saveInBackground(new SaveCallback() {
                                @Override
                                public void done(ParseException e) {
                                    if(e==null){
                                        Toast.makeText(Leader_complaint_description.this, "Status Updated Successfully", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                        }
                    }
                });

            }
        });


        statuses.add("Pending");
        statuses.add("Working");
        statuses.add("Solved");
        statuses.add("Rejected");
        statuses.add("Order Given");

        ad = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, statuses);
        stats.setAdapter(ad);
        ParseQuery<ParseUser> pq = new ParseUser().getQuery();
        pq.whereEqualTo("username", user);
        pq.findInBackground(new FindCallback<ParseUser>() {
            @Override
            public void done(List<ParseUser> list, ParseException e) {
                if (e == null) {
                    if (list.size() > 0) {
                        String email = list.get(0).getString("email");
                        String phone = list.get(0).getString("phone");
                        String add = list.get(0).getString("address");
                        uCredentials = email + "\n" + phone + "\n" + add;
                    }
                }
            }
        });

        ParseQuery<ParseObject> po = new ParseQuery<ParseObject>("Compliants");
        po.whereEqualTo("uname", user);
        po.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> list, ParseException e) {
                if (e == null) {
                    String Complaint_title = "Subject:  " + list.get(0).getString("compliant_title");
                    String Complaint_desc = list.get(0).getString("compliant_description");
                    String Complaint_status = list.get(0).getString("complaint_status");
                    Date Complaint_date = list.get(0).getCreatedAt();
                    DateFormat df = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                    String d = df.format(Complaint_date);
                    String head = "From\n" + user + "\n" + uCredentials + "\n\nTo\nNavjot Singh Sidhu\nMLA West Amritsar\n\n" + Complaint_title + "\n" + Complaint_desc;
                    complaint_desc.setText(head);
                    complaint_date.setText(d);
                    int jk=statuses.indexOf(Complaint_status);
                    statuses.set(jk,"Pending");
                    statuses.set(0,Complaint_status);
                    ad.notifyDataSetChanged();
                }
            }
        });


    }
}
