package com.vector.medicus1;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class PopUp extends AppCompatActivity {
    Button book,loc,chat;
    RadioGroup radioGroup;
    RadioButton rb1,rb2,rb3,rb4;
    String doctorName = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop_up);
        Intent intent = getIntent();

        doctorName = intent.getStringExtra("name");
        chat=(Button)findViewById(R.id.chat);
        book=(Button)findViewById(R.id.book);
        loc=(Button)findViewById(R.id.loc);
        radioGroup=(RadioGroup) findViewById(R.id.radioGroup2);
        rb1=(RadioButton)findViewById(R.id.rb1);
        rb2=(RadioButton)findViewById(R.id.rb2);
        rb3=(RadioButton)findViewById(R.id.rb3);
        rb4=(RadioButton)findViewById(R.id.rb4);
        loc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent il=new Intent(PopUp.this,MapsActivity.class);
                startActivity(il);
            }
        });

        chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent il2 =new Intent(PopUp.this,Login.class);
                startActivity(il2);
            }
        });

        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id = radioGroup.getCheckedRadioButtonId();
                if (id == R.id.rb1)
                    new checkSlot().execute(1);
                if (id == R.id.rb2)
                    new checkSlot().execute(2);
                if (id == R.id.rb3)
                    new checkSlot().execute(3);
                if (id == R.id.rb4)
                    new checkSlot().execute(4);
            }
        });
    }

    public class checkSlot extends AsyncTask<Integer,Void,Void> {
        String data = "";
        int slotnumer = 0;
        ProgressDialog progressDialog;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = ProgressDialog.show(PopUp.this, "Please Wait", "Checking Slot");
        }

        @Override
        protected Void doInBackground (Integer...integers){
            try {
                slotnumer = integers[0];
                URL url = new URL("http://172.20.10.13/webapp/checkdocslot.php?name="+doctorName+"&slot="+integers[0]);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String line = "";
                while ((line=bufferedReader.readLine())!= null) {
                    data = data + line;
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            progressDialog.dismiss();
            if(data.trim().equals("available"))
            {
                new bookSlot().execute(slotnumer);
            }
            else
                Toast.makeText(PopUp.this, "Slot not available", Toast.LENGTH_SHORT).show();
        }
    }

    public class bookSlot extends AsyncTask<Integer,Void,Void> {
        String data = "";
        ProgressDialog progressDialog;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = ProgressDialog.show(PopUp.this, "Please Wait", "Booking Slot");
        }

        @Override
        protected Void doInBackground (Integer...integers){
            try {
                URL url = new URL("http://172.20.10.13/webapp/bookdocslot.php?name="+doctorName+"&slot="+integers[0]);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String line = "";
                while ((line=bufferedReader.readLine())!= null) {
                    data = data + line;
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            progressDialog.dismiss();
            if(data.trim().equals("success"))
                Toast.makeText(PopUp.this, "Slot booked successfully", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(PopUp.this, "Something went wrong.\nPlease try again.", Toast.LENGTH_SHORT).show();
        }
    }
}