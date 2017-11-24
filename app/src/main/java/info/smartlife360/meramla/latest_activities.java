package info.smartlife360.meramla;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.GetDataCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class latest_activities extends AppCompatActivity implements AdapterView.OnItemClickListener {

    ListView list_activity;
    public static final ArrayList<Bitmap> Array_bit=new ArrayList<>();
    public static final ArrayList<String> array_title=new ArrayList<>();
    public static final ArrayList<String> array_date=new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_latest_activities);
        list_activity=(ListView)findViewById(R.id.list_activity);
        final Basadapter bd=new Basadapter();
        list_activity.setAdapter(bd);
        list_activity.setOnItemClickListener(this);

        ParseQuery<ParseObject> po=new ParseQuery<>("activity_leader");
        po.addDescendingOrder("createdAt");
        po.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> list, ParseException e) {
                if(e==null && list.size()>0)
                {
                    for(ParseObject ps:list){
                        array_title.add( ps.getString("title"));
                        Date date=ps.getCreatedAt();
                        DateFormat df=new SimpleDateFormat("dd-MM-yyy");
                        array_date.add(df.format(date));
                        bd.notifyDataSetChanged();

                        ParseFile pf=(ParseFile)ps.get("image");
                        pf.getDataInBackground(new GetDataCallback() {
                            @Override
                            public void done(byte[] bytes, ParseException e) {
                                if(e==null)
                                {
                                    Bitmap bt= BitmapFactory.decodeByteArray(bytes,0,bytes.length);
                                    Array_bit.add(bt);
                                    bd.notifyDataSetChanged();
                                }
                            }
                        });
                    }
                }
            }
        });
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent i=new Intent(getApplicationContext(),Leader_Activity_Detail.class);
        i.putExtra("title",position);
        startActivity(i);
    }


    public class Basadapter extends BaseAdapter{
        @Override
        public int getCount() {
            return Array_bit.size();
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
            convertView=getLayoutInflater().inflate(R.layout.show_all_activities_list,null);
            TextView textView21=(TextView)convertView.findViewById(R.id.textView21);
            TextView textView24=(TextView)convertView.findViewById(R.id.textView28);
            ImageView imageView2=(ImageView)convertView.findViewById(R.id.imageView2);

            textView21.setText(array_title.get(position));
            textView24.setText(array_date.get(position));
            imageView2.setImageBitmap(Array_bit.get(position));

            return convertView;
        }
    }
}
