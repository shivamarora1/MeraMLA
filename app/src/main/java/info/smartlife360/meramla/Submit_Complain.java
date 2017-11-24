package info.smartlife360.meramla;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SaveCallback;

public class Submit_Complain extends AppCompatActivity implements View.OnClickListener {

    EditText complaint_text;
    EditText compliant_description;
    Button compliant_reset;
    Button complaint_lodge;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit__complain);
        complaint_text=(EditText)findViewById(R.id.complaint_text);
        compliant_description=(EditText)findViewById(R.id.compliant_description);
        compliant_reset=(Button)findViewById(R.id.compliant_reset);
        compliant_reset.setOnClickListener(this);
        complaint_lodge=(Button)findViewById(R.id.complaint_lodge);
        complaint_lodge.setOnClickListener(this);



    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.compliant_reset)
        {
            complaint_text.setText("");
            compliant_description.setText("");
        }
        else {
            if (v.getId() == R.id.complaint_lodge) {
                if(complaint_text.getText().equals("") &&compliant_description.getText().equals("") ){
                    Toast.makeText(this, "Complaint Description can not be empty...", Toast.LENGTH_SHORT).show();
                }
                else{
                    ParseObject complaints = new ParseObject("Compliants");
                    complaints.put("uname", ParseUser.getCurrentUser().getUsername());
                    complaints.put("compliant_title", complaint_text.getText().toString());
                    complaints.put("compliant_description", compliant_description.getText().toString());
                    complaints.put("complaint_status","Pending");
                    complaints.saveInBackground(new SaveCallback() {
                        @Override
                        public void done(ParseException e) {
                            if (e == null) {
                                Toast.makeText(Submit_Complain.this, "Complaint Submitted Successfully", Toast.LENGTH_SHORT).show();
                                complaint_text.setText("");
                                compliant_description.setText("");
                            }
                            else{
                                e.printStackTrace();
                                Toast.makeText(Submit_Complain.this, "Sorry!! Error Occurred", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }

            }
        }

    }
}
