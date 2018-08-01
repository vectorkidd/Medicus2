package com.vector.medicus1;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.LinearLayout;

public class PatDetails extends AppCompatActivity {
    public static LinearLayout linearLayout;
    public static Context context = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pat_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        context = this;

        linearLayout = findViewById(R.id.linearlayout2);

        Fetchpat fp=new Fetchpat();
        fp.execute();
    }
}
