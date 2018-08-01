package com.vector.medicus1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DocDets extends AppCompatActivity {
    Button pax,chats;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doc_dets);

        pax=(Button)findViewById(R.id.paxdet);
        pax.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent il=new Intent(DocDets.this,PatDetails.class);
                startActivity(il);
            }
        });

        chats=(Button)findViewById(R.id.chats);
        chats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent il1=new Intent(DocDets.this,Login.class);
                startActivity(il1);
            }
        });

    }
}
