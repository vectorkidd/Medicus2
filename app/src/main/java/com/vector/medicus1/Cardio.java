package com.vector.medicus1;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Cardio extends AppCompatActivity {
    public static LinearLayout linearLayout;
    public static Context context = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cardio1);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        context = this;

        linearLayout = findViewById(R.id.docdata);

        //new CustomAdapter(Cardio.this,urlAddress,lv).execute();
        fetchData process=new fetchData();
        process.execute();
    }



        //setSingleEvent(gridLayout);


    /*private void load_data_from_server(int i) {

        final ProgressDialog progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Loading..");
        progressDialog.show();

        StringRequest stringRequest=new StringRequest(Request.Method.GET, data_url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();

                        try{
                            JSONArray jsonArray=new JSONArray(response);
                            for(int i=0;i<jsonArray.length();i++){
                                JSONObject details=jsonArray.getJSONObject(i);

                                data_list.add(new Docdetails(

                                        details.getString("dfname"),
                                        details.getInt("ddob"),
                                        details.getString("dgender"),
                                        details.getString("speciality"),
                                        details.getString("hospital"),
                                        details.getInt("linum")
                                ));
                            }

                            CustomAdapter customAdapter=new CustomAdapter(Cardio.this,data_list);
                            recyclerView.setAdapter(customAdapter);

                        }catch (JSONException e){
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Cardio.this,error.getMessage(),Toast.LENGTH_SHORT).show();

                    }
                });
        Volley.newRequestQueue(this).add(stringRequest);
    }*/




}
