package com.vector.medicus1;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Dermat extends AppCompatActivity {
    public static LinearLayout linearLayout;
    public static Context context = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dermat1);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        context=this;

        linearLayout=(findViewById(R.id.linearlayout));

        fetchDerm fd=new fetchDerm();
        fd.execute();

    }
}
