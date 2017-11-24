package info.smartlife360.meramla;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

public class Leader_Activity_Detail extends AppCompatActivity {

    ImageView imageView5;
    WebView textView24;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leader___detail);
        imageView5=(ImageView)findViewById(R.id.imageView5);
        textView24=(WebView) findViewById(R.id.textView24);

        int i=getIntent().getIntExtra("title",0);
        Bitmap b=latest_activities.Array_bit.get(i);
       imageView5.setImageBitmap(b);
        String g=latest_activities.array_title.get(i);
        textView24.loadData("<p style=\"text-align:justify\">"+g+"</p>","text/html","UTF-8");


    }

}
