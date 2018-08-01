package com.vector.medicus1;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.View;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Fetchpat extends AsyncTask<Void,Void,Void> {

    String data = "";
    String dataParsed = "";
    String singleParsed = "";
    ProgressDialog progressDialog;

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog = ProgressDialog.show(PatDetails.context, "Please Wait", "Fetching Doctors");
    }

    @Override
    protected Void doInBackground(Void... voids) {
        try {


            URL url = new URL("http://172.20.10.13/webapp/fetchpat.php");
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line = "";
            while (line != null) {
                line = bufferedReader.readLine();
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

        JSONArray JA = null;
        try {
            JA = new JSONArray(data);
            for (int i = 0; i < JA.length(); i++) {
                final JSONObject JO = JA.getJSONObject(i);
                singleParsed = "Fullname:" + JO.get("Fullname") + "\n" +
                        "Age:" + JO.get("Age") + "\n" +
                        "Gender:" + JO.get("Gender") + "\n" +
                        "Blood Group:" + JO.get("BloodGroup") + "\n" +
                        "Mobile Number:" + JO.get("MobileNo") + "\n";
                dataParsed += singleParsed + "\n";

                TextView textView = new TextView(PatDetails.context);
                textView.setText(singleParsed);

                PatDetails.linearLayout.addView(textView);
                TextView textView1 = new TextView(PatDetails.context);
                textView1.setPadding(8,8,8,8);
                PatDetails.linearLayout.addView(textView1);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
