package com.vector.medicus1;

import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;

public class RegP extends AppCompatActivity{
    EditText fname,dob, mobnum, gender;
    Button save;
    EditText bldgrp;
    TextView tv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg_p);
        StrictMode.ThreadPolicy policy=new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);


        fname = (EditText) findViewById(R.id.fullname);
        gender=(EditText)findViewById(R.id.gender);
        dob = (EditText) findViewById(R.id.dob);
        mobnum = (EditText) findViewById(R.id.mobno);
        bldgrp = (EditText) findViewById(R.id.bgroup);
        tv=(TextView) findViewById(R.id.textView4);
        save=(Button) findViewById(R.id.button2);

        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent il=new Intent(RegP.this,paxprof.class);
                startActivity(il);
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sfname=fname.getText().toString();
                String sdob=dob.getText().toString();
                String sbldgrp=bldgrp.getText().toString();
                String smobnum=mobnum.getText().toString();
                String sgender=gender.getText().toString();

                String method="register";

                BackgroundTask backgroundTask=new BackgroundTask(RegP.this);
                backgroundTask.execute(method,sfname,sdob,sgender,sbldgrp,smobnum);

                if (fname.getText().toString().trim().equalsIgnoreCase("")) {
                    fname.setError("This field can not be blank");
                }
                else if(dob.getText().toString().trim().equalsIgnoreCase("")) {
                    dob.setError("This field can not be blank");
                }
                else if(gender.getText().toString().trim().equalsIgnoreCase("")) {
                    gender.setError("This field can not be blank");
                }
                else if(bldgrp.getText().toString().trim().equalsIgnoreCase("")) {
                    bldgrp.setError("This field can not be blank");
                }
                else if(mobnum.getText().toString().trim().equalsIgnoreCase("")) {
                    mobnum.setError("This field can not be blank");
                }
                else{
                    Intent il=new Intent(RegP.this,paxprof.class);
                    startActivity(il);
                }


            }
        });
    }

}
