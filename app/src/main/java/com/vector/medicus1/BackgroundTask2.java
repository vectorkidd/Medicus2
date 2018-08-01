package com.vector.medicus1;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
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

public class BackgroundTask2 extends AsyncTask<String,Void,String> {


    Context ctx;
    BackgroundTask2(Context ctx){
        this.ctx=ctx;
    }

    @Override
    protected void onPreExecute() {

        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... params) {
        String dreg_url="http://172.20.10.13/webapp/registerdoc.php";
        String method=params[0];
        String sdfname=params[1];
        String sddob=params[2];
        String sdgender=params[3];
        String sspeciality=params[4];
        String shospital=params[5];
        String slinum=params[6];
        try {
            URL url=new URL(dreg_url);
            HttpURLConnection httpURLConnection=(HttpURLConnection)url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            OutputStream outputStream = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
            String post_data = URLEncoder.encode("Dfullname", "UTF-8") + "=" + URLEncoder.encode(sdfname, "UTF-8") + "&"
                    + URLEncoder.encode("Ddob", "UTF-8") + "=" + URLEncoder.encode(sddob, "UTF-8") + "&"
                    + URLEncoder.encode("Dgender", "UTF-8") + "=" + URLEncoder.encode(sdgender, "UTF-8") + "&"
                    + URLEncoder.encode("Dspeciality", "UTF-8") + "=" + URLEncoder.encode(sspeciality, "UTF-8") + "&"
                    + URLEncoder.encode("Dhospital", "UTF-8") + "=" + URLEncoder.encode(shospital, "UTF-8") + "&"
                    + URLEncoder.encode("Dlinum", "UTF-8") + "=" + URLEncoder.encode(slinum, "UTF-8");
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
