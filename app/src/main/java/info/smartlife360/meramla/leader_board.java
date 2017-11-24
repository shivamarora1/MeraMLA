package info.smartlife360.meramla;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class leader_board extends AppCompatActivity implements AdapterView.OnItemClickListener {
    final ArrayList<String> aa=new ArrayList<>();
    final ArrayList<Date> dates=new ArrayList<>();
    final ArrayList<String> status1=new ArrayList<>();
    final ArrayList<String> usname=new ArrayList<>();
    final ArrayList<String> no_name=new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leader_board);

        final ListView list1=(ListView)findViewById(R.id.list1);

        list1.setOnItemClickListener(this);

        final ArrayAdapter aAdapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1,aa);
        ParseQuery<ParseObject> pi=new ParseQuery<ParseObject>("Compliants");
        pi.addDescendingOrder("createdAt");
        pi.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> list, ParseException e) {
                if(e==null)
                {
                 for(ParseObject ps:list){
                     aa.add(ps.getString("compliant_title"));
                     status1.add(ps.getString("complaint_status"));
                     usname.add(ps.getString("uname"));
                     dates.add(ps.getCreatedAt());
                 }
                    list1.setAdapter(new Custom_Adapter());
                }
            }

        });

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent i=new Intent(getApplicationContext(),Leader_complaint_description.class);
        i.putExtra("uname",usname.get(position));
        startActivity(i);
    }

    class Custom_Adapter extends BaseAdapter{

        @Override
        public int getCount() {
            return aa.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            convertView=getLayoutInflater().inflate(R.layout.template_all_compliants,null);
            TextView textView15=(TextView)convertView.findViewById(R.id.textView15);
            TextView uname=(TextView)convertView.findViewById(R.id.textView12);
            TextView status=(TextView)convertView.findViewById(R.id.textView16);
            TextView date=(TextView)convertView.findViewById(R.id.textView17);

            DateFormat df=new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            String d=df.format(dates.get(position));

            textView15.setText(aa.get(position));
            uname.setText(usname.get(position));
            status.setText(status1.get(position));
            date.setText(d);

            return convertView;
        }
    }
}
