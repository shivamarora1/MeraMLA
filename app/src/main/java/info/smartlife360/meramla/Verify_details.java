package info.smartlife360.meramla;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SaveCallback;

public class Verify_details extends AppCompatActivity {

    TextView text_name;
    EditText text_add;
    EditText text_email;
    EditText text_phone;
    Button button_submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_details);

        text_name=(TextView)findViewById(R.id.text_name);
        text_add=(EditText)findViewById(R.id.text_add);
        text_email=(EditText)findViewById(R.id.text_email);
        text_phone=(EditText)findViewById(R.id.text_phone);
        button_submit=(Button)findViewById(R.id.button_submit);
        String add=ParseUser.getCurrentUser().getString("address");
        String phone=ParseUser.getCurrentUser().getString("phone");
        String email=ParseUser.getCurrentUser().getEmail();

        text_add.setText(add);
        text_email.setText(email);
        text_phone.setText(phone);

        button_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(text_add.getText().equals("")||text_email.getText().equals("")||text_phone.getText().equals("")){
                    Toast.makeText(Verify_details.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                }

                else{

                    ParseUser.getCurrentUser().put("address",text_add.getText()+"");
                    ParseUser.getCurrentUser().setEmail(text_email.getText()+"");
                    ParseUser.getCurrentUser().put("phone",text_phone.getText()+"");
                    ParseUser.getCurrentUser().saveInBackground(new SaveCallback() {
                        @Override
                        public void done(ParseException e) {
                            if(e==null)
                            {
                                Toast.makeText(Verify_details.this, "Data Saved Successfully", Toast.LENGTH_SHORT).show();
                                Intent i=new Intent(getApplicationContext(),MainActivity.class);
                                startActivity(i);
                            }
                            else{
                                Toast.makeText(Verify_details.this, "Some Error Occured", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });

        String uname= ParseUser.getCurrentUser().getUsername();
        text_name.setText("Welcome "+uname);
    }
}
