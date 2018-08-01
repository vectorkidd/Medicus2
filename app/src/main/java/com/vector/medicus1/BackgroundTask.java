package com.vector.medicus1;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by Khalid on 3/3/2018.
 */

public class BackgroundTask extends AsyncTask<String,Void,String> {

    Context ctx;
    BackgroundTask(Context ctx){
        this.ctx=ctx;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... params) {
        Log.d("Test","error");
        String reg_url="http://172.20.10.13/webapp/register.php";

        String method=params[0];
        String sfname=params[1];
        String sdob=params[2];
        String sgender=params[3];
        String sbldgrp=params[4];
        String smobnum=params[5];
        try {
            URL url=new URL(reg_url);
            HttpURLConnection httpURLConnection=(HttpURLConnection)url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            OutputStream outputStream = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
            String post_data = URLEncoder.encode("Fullname", "UTF-8") + "=" + URLEncoder.encode(sfname, "UTF-8") + "&"
                    + URLEncoder.encode("Age", "UTF-8") + "=" + URLEncoder.encode(sdob, "UTF-8") + "&"
                    + URLEncoder.encode("Gender", "UTF-8") + "=" + URLEncoder.encode(sgender, "UTF-8") + "&"
                    + URLEncoder.encode("BloodGroup", "UTF-8") + "=" + URLEncoder.encode(sbldgrp, "UTF-8") + "&"
                    + URLEncoder.encode("MobileNo", "UTF-8") + "=" + URLEncoder.encode(smobnum, "UTF-8");
            bufferedWriter.write(post_data);
            bufferedWriter.flush();
            bufferedWriter.close();
            outputStream.close();
            InputStream inputStream = httpURLConnection.getInputStream();
            inputStream.close();
            return "success";
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return null;
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(String result) {
        Toast.makeText(ctx,result,Toast.LENGTH_SHORT).show();
    }
}

