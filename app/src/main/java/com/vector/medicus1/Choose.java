package com.vector.medicus1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Choose extends AppCompatActivity {
    Button pax,doc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);
        pax=(Button)findViewById(R.id.patient);
        doc=(Button)findViewById(R.id.doc);
        pax.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent il=new Intent(Choose.this, RegP.class);
                startActivity(il);
            }
        });

        doc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent il=new Intent(Choose.this,RegD.class);
                startActivity(il);
            }
        });
    }
}
