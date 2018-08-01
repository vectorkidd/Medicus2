package com.vector.medicus1;

import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;

public class Offhelp extends android.support.v4.app.Fragment {

    Button amb,fire,child,blood,wom;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ((paxprof)getActivity()).SetActionBarTitle("Offline Help");
        View v= inflater.inflate(R.layout.activity_offhelp,null);
        amb=(Button)v.findViewById(R.id.ambnum);
        fire=(Button)v.findViewById(R.id.firenum);
        child=(Button)v.findViewById(R.id.childnum);
        blood=(Button)v.findViewById(R.id.bldre);
        wom=(Button)v.findViewById(R.id.whelpline);

        amb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent il=new Intent(Intent.ACTION_CALL);
                il.setData(Uri.parse("tel:102"));
                startActivity(il);
            }
        });

        fire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent il1=new Intent(Intent.ACTION_CALL);
                il1.setData(Uri.parse("tel:101"));
                startActivity(il1);
            }
        });
        child.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent il2=new Intent(Intent.ACTION_CALL);
                il2.setData(Uri.parse("tel:1098"));
                startActivity(il2);
            }
        });
        blood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent il3=new Intent(Intent.ACTION_CALL);
                il3.setData(Uri.parse("tel:104"));
                startActivity(il3);
            }
        });
        wom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent il4=new Intent(Intent.ACTION_CALL);
                il4.setData(Uri.parse("tel:181"));
                startActivity(il4);
            }
        });


        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.findViewById(R.id.scrollView1);


    }
}
