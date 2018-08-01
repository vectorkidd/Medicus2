package com.vector.medicus1;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Splash extends AppCompatActivity {
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        tv=(TextView)findViewById(R.id.textView7);
        Thread t1=new Thread(){
            public void run(){
                try{
                    sleep(2000);
                }
                catch(Exception e){
                    e.printStackTrace();
                }
                finally{
                    Intent il1=new Intent(Splash.this,Login.class);
                    startActivity(il1);
                }
            }
        };t1.start();

        Typeface myfont=Typeface.createFromAsset(getAssets(),"fonts/BLKCHCRY.TTF");
        tv.setTypeface(myfont);

    }
}
