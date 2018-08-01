package com.vector.medicus1;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

/**
 * Created by Khalid on 2/27/2018.
 */

public class FragmentSlct extends Fragment {
        RadioGroup radioGroup;


    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ((paxprof)getActivity()).SetActionBarTitle("Select Doctor");
        View v= inflater.inflate(R.layout.fragment_slct, container,false);
        radioGroup= v.findViewById(R.id.radioGroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedID) {
                switch(checkedID){
                    case R.id.cardio:
                        Intent il=new Intent(getActivity(),Cardio.class);
                        startActivity(il);

                        break;

                    case R.id.dermat:
                        Intent il1=new Intent(getActivity(),Dermat.class);
                        startActivity(il1);


                        break;

                    case R.id.ortho:
                        Intent il2=new Intent(getActivity(),Ortho.class);
                        startActivity(il2);

                        break;

                    case R.id.neuro:
                        Intent il3=new Intent(getActivity(),Neuro.class);
                        startActivity(il3);

                        break;

                    case R.id.genpract:
                        Intent il4=new Intent(getActivity(),Gen.class);
                        startActivity(il4);

                        break;

                }
            }
        });

        return v;

    }


    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        /*view.findViewById(R.id.cardio);
        view.findViewById(R.id.dermat);
        view.findViewById(R.id.neuro);
        view.findViewById(R.id.ortho);
        view.findViewById(R.id.genpract);
        view.findViewById(R.id.btnslct);*/

    }
}
