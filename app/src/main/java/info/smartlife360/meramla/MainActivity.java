package info.smartlife360.meramla;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button button_add;
    Button button_view;
    Button button_know;
    Button button_latest;
    Button button_Ver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button_add=(Button)findViewById(R.id.button_add);
        button_add.setOnClickListener(this);
        button_view=(Button)findViewById(R.id.button_view);
        button_view.setOnClickListener(this);
        button_know=(Button)findViewById(R.id.button_know);
        button_know.setOnClickListener(this);
        button_latest=(Button)findViewById(R.id.button_latest);
        button_latest.setOnClickListener(this);
        button_Ver=(Button)findViewById(R.id.button_Ver);
        button_Ver.setOnClickListener(this);
    }

    @Override
    public void onBackPressed()
    {
        AlertDialog.Builder alert=new AlertDialog.Builder(this);
        alert.setTitle("Logout")
                .setCancelable(false)
                .setMessage("Are you sure to Logout....")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finishAndRemoveTask();
                        Intent i=new Intent(getApplicationContext(),Log_in.class);
                        startActivity(i);
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        AlertDialog ag=alert.create();
        ag.show();
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.button_know)
        {
            Intent i=new Intent(getApplicationContext(),know_mla.class);
            startActivity(i);
        }
        else if(v.getId()==R.id.button_add)
        {
            Intent i=new Intent(getApplicationContext(),Submit_Complain.class);
            startActivity(i);
        }
        else if(v.getId()==R.id.button_view)
        {
            Intent i=new Intent(getApplicationContext(),view_status.class);
            startActivity(i);
        }
        else if(v.getId()==R.id.button_latest)
        {
            Intent i=new Intent(getApplicationContext(),latest_activities.class);
            startActivity(i);
        }
        else if(v.getId()==R.id.button_Ver)
        {
            Intent i=new Intent(getApplicationContext(),Verify_details.class);
            startActivity(i);
        }

    }
}
