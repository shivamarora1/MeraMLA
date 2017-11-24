package info.smartlife360.meramla;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class Log_in extends AppCompatActivity implements View.OnClickListener {

    Button login_button;
    TextView signup_button;
    TextView edittext;
    TextView edittext2;
    boolean loginTrue=true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        login_button =(Button)findViewById(R.id.login_button);
        signup_button=(TextView) findViewById(R.id.signup_textView);
        edittext=(TextView)findViewById(R.id.editText);
        edittext2=(TextView)findViewById(R.id.editText2);

        login_button.setOnClickListener(this);
        signup_button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch(v.getId()){
            case R.id.login_button:
            {
                ParseUser.logOut();
                if(loginTrue)
                        {
                            try {
                                String uname=edittext.getText().toString();
                                String pass=edittext2.getText().toString();
                                if(uname.equals("netaji")&&pass.equals("netaji")){
                                    finishAndRemoveTask();
                                    Intent i=new Intent(getApplicationContext(),Leader_main.class);
                                    startActivity(i);
                                }
                                else{
                                    ParseUser.logIn(uname,pass);
                                    finishAndRemoveTask();
                                    Intent i=new Intent(getApplicationContext(),MainActivity.class);
                                    startActivity(i);
                                }
                            } catch (ParseException e)
                            {
                                Toast.makeText(this, "Invalid Email Id or Password...", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else
                            {
                                ParseUser user=new ParseUser();
                                user.setUsername(edittext.getText().toString());
                                user.setPassword(edittext2.getText().toString());
                                user.signUpInBackground(new SignUpCallback() {
                                    @Override
                                    public void done(ParseException e) {
                                        if(e!=null)
                                        {
                                            e.printStackTrace();
                                            Toast.makeText(Log_in.this, "Some Error Occured...", Toast.LENGTH_SHORT).show();
                                        }
                                        else{
                                            Toast.makeText(Log_in.this, "SignUp Successful", Toast.LENGTH_SHORT).show();
                                            finishAndRemoveTask();
                                            Intent i=new Intent(getApplicationContext(),Verify_details.class);
                                            startActivity(i);
                                        }
                                    }
                                });

                        }

                        break;
            }

            case R.id.signup_textView:
            {
                if(loginTrue)
                {
                    login_button.setText("SignUp");
                    signup_button.setText("LogIn");
                    loginTrue=false;
                }
                else{
                    login_button.setText("LogIn");
                    signup_button.setText("SignUp");
                    loginTrue=true;
                }
                break;

            }
        }
    }
}
