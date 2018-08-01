package com.vector.medicus1;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Ortho extends AppCompatActivity {
    public static LinearLayout linearLayout;
    public static Context context = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ortho);

        context=this;

        linearLayout=(findViewById(R.id.linearlayout));

        fetchOrtho fo=new fetchOrtho();
        fo.execute();
    }
}
