package com.vector.medicus1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class RegD extends AppCompatActivity {

    EditText dfname, ddob, dgender, speciality, hospital, linum;
    TextView click;
    Button dsave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg_d);

        dfname = (EditText) findViewById(R.id.dfullname);
        ddob = (EditText) findViewById(R.id.ddob);
        dgender = (EditText) findViewById(R.id.dgender);
        speciality = (EditText) findViewById(R.id.speciality);
        hospital = (EditText) findViewById(R.id.dhospital);
        linum = (EditText) findViewById(R.id.linum);
        click = (TextView) findViewById(R.id.textVi);
        dsave = (Button) findViewById(R.id.dsave);
        click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent il=new Intent(RegD.this,DocDets.class);
                startActivity(il);
            }
        });

        dsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sdfname=dfname.getText().toString();
                String sddob=ddob.getText().toString();
                String sdgender=dgender.getText().toString();
                String sspeciality=speciality.getText().toString();
                String shospital=hospital.getText().toString();
                String slinum=linum.getText().toString();

                String method="dregister";

                BackgroundTask2 backgroundTask2=new BackgroundTask2(RegD.this);
                backgroundTask2.execute(method,sdfname,sddob,sdgender,sspeciality,shospital,slinum);
                if (dfname.getText().toString().trim().equalsIgnoreCase("")) {
                    dfname.setError("This field can not be blank");
                }
                else if(ddob.getText().toString().trim().equalsIgnoreCase("")) {
                    ddob.setError("This field can not be blank");
                }
                else if(dgender.getText().toString().trim().equalsIgnoreCase("")) {
                    dgender.setError("This field can not be blank");
                }
                else if(speciality.getText().toString().trim().equalsIgnoreCase("")) {
                    speciality.setError("This field can not be blank");
                }
                else if(hospital.getText().toString().trim().equalsIgnoreCase("")) {
                    hospital.setError("This field can not be blank");
                }
                else if(linum.getText().toString().trim().equalsIgnoreCase("")) {
                    linum.setError("This field can not be blank");
                }
                else{
                    Intent il=new Intent(RegD.this,DocDets.class);
                    startActivity(il);
                }
            }
        });
    }
}
