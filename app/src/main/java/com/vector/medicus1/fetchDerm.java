package com.vector.medicus1;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
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
import java.net.MalformedURLException;
import java.net.URL;



/**
 * Created by Khalid on 3/20/2018.
 */

public class fetchDerm extends AsyncTask<Void ,Void ,Void> {

    String data = "";
    String dataParsed = "";
    String singleParsed = "";
    ProgressDialog progressDialog;

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog=ProgressDialog.show(Dermat.context,"Please Wait","Fetching Doctors");
    }

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            URL url = new URL("http://172.20.10.13/webapp/fetch1.php");
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line = "";
            while (line != null) {
                line = bufferedReader.readLine();
                data = data + line;
            }
        }  catch (IOException e) {
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
                singleParsed = "Fullname:" + JO.get("Dfullname") + "\n" +
                        "Age:" + JO.get("Ddob") + "\n" +
                        "Gender:" + JO.get("Dgender") + "\n" +
                        "Speciality:" + JO.get("Dspeciality") + "\n" +
                        "Hospital:" + JO.get("Dhospital") + "\n" +
                        "Linum:" + JO.get("Dlinum") + "\n";
                dataParsed += singleParsed + "\n";

                TextView textView = new TextView(Dermat.context);
                textView.setText(singleParsed);
                textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent il = new Intent(Dermat.context, PopUp.class);
                        try {
                            il.putExtra("name", JO.get("Dfullname").toString());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        Dermat.context.startActivity(il);
                    }
                });
                Dermat.linearLayout.addView(textView);
                TextView textView1 = new TextView(Dermat.context);
                textView1.setPadding(8,8,8,8);
                Dermat.linearLayout.addView(textView1);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}

class fetchNeuro extends AsyncTask<Void, Void, Void> {
    String data = "";
    String dataParsed = "";
    String singleParsed = "";
    ProgressDialog progressDialog;

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog=ProgressDialog.show(Neuro.context,"Please Wait","Fetching Doctors");
    }

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            URL url = new URL("http://172.20.10.13/webapp/fetchneuro.php");
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
                singleParsed = "Fullname:" + JO.get("Dfullname") + "\n" +
                        "Age:" + JO.get("Ddob") + "\n" +
                        "Gender:" + JO.get("Dgender") + "\n" +
                        "Speciality:" + JO.get("Dspeciality") + "\n" +
                        "Hospital:" + JO.get("Dhospital") + "\n" +
                        "Linum:" + JO.get("Dlinum") + "\n";
                dataParsed += singleParsed + "\n";

                TextView textView = new TextView(Neuro.context);
                textView.setText(singleParsed);
                textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent il = new Intent(Neuro.context, PopUp.class);
                        try {
                            il.putExtra("name", JO.get("Dfullname").toString());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        Neuro.context.startActivity(il);
                    }
                });
                Neuro.linearLayout.addView(textView);
                TextView textView1 = new TextView(Neuro.context);
                textView1.setPadding(8,8,8,8);
                Neuro.linearLayout.addView(textView1);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}


class fetchOrtho extends AsyncTask<Void, Void, Void> {
    String data = "";
    String dataParsed = "";
    String singleParsed = "";
    ProgressDialog progressDialog;

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog=ProgressDialog.show(Ortho.context,"Please Wait", "Fetching Doctors");
    }

    @Override
            protected Void doInBackground(Void... voids) {
                try {
                    URL url = new URL("http://172.20.10.13/webapp/fetchortho.php");
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
                        singleParsed = "Fullname:" + JO.get("Dfullname") + "\n" +
                                "Age:" + JO.get("Ddob") + "\n" +
                                "Gender:" + JO.get("Dgender") + "\n" +
                                "Speciality:" + JO.get("Dspeciality") + "\n" +
                                "Hospital:" + JO.get("Dhospital") + "\n" +
                                "Linum:" + JO.get("Dlinum") + "\n";
                        dataParsed += singleParsed + "\n";

                        TextView textView = new TextView(Ortho.context);
                        textView.setText(singleParsed);
                        textView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent il = new Intent(Ortho.context, PopUp.class);
                                try {
                                    il.putExtra("name", JO.get("Dfullname").toString());
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                Ortho.context.startActivity(il);
                            }
                        });
                        Ortho.linearLayout.addView(textView);
                        TextView textView1 = new TextView(Ortho.context);
                        textView1.setPadding(8,8,8,8);
                        Ortho.linearLayout.addView(textView1);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();

                }
            }

}
