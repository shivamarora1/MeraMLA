package info.smartlife360.meramla;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.SaveCallback;

import java.io.ByteArrayOutputStream;

public class Leader_activity extends AppCompatActivity implements View.OnClickListener {

    EditText editText3;
    Button upload_image;
    Button save;
    Bitmap b;
    ImageView choosen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leader_activity);

        this.setTitle("Add a new activity...");
        editText3=(EditText)findViewById(R.id.editText3);
        upload_image=(Button)findViewById(R.id.button2);
        save=(Button)findViewById(R.id.button3);
        choosen=(ImageView)findViewById(R.id.imageView4);

        upload_image.setOnClickListener(this);
        save.setOnClickListener(this);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1 && resultCode==RESULT_OK && data!=null)
        {
            Uri selectedImage=data.getData();
            try{
                b=MediaStore.Images.Media.getBitmap(this.getContentResolver(),selectedImage);
                choosen.setImageBitmap(b);
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==1)
        {
            if(grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED)
            {
                Intent intent=new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent,1);
            }
        }
    }


    @Override
    public void onClick(View v) {
        switch(v.getId())
        {
            case R.id.button2:
            {
                if(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED)
                {
                    ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},1);
                }
                else{
                    Intent intent=new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(intent,1);
                }
                break;
            }
            case R.id.button3:
            {
                ByteArrayOutputStream str=new ByteArrayOutputStream();
                b.compress(Bitmap.CompressFormat.PNG,100,str);
                byte[] byteArr=str.toByteArray();
                ParseFile file=new ParseFile("ima.png",byteArr);
                ParseObject parseObject=new ParseObject("activity_leader");
                parseObject.put("image",file);
                parseObject.put("title",editText3.getText().toString());
                parseObject.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(ParseException e) {
                        if(e==null){
                            Toast.makeText(Leader_activity.this, "New Activity Shared Successfully...", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            break;
            }
        }
    }
}
