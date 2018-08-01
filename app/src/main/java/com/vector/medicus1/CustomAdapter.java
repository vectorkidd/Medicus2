package com.vector.medicus1;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Khalid on 3/10/2018.
 */

public class CustomAdapter extends AsyncTask<Void,Void,String> {
    Context c;
    String urlAddess;
    ListView lv;

    ProgressDialog pd;



    public CustomAdapter(Context c, String urlAddess, ListView lv) {
        this.c = c;
        this.urlAddess = urlAddess;
        this.lv = lv;
    }

    @Override
    protected String doInBackground(Void... voids) {
        return downloadData();
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        pd=new ProgressDialog(c);
        pd.setTitle("Retrieve");
        pd.setMessage("Retrieving..Please wait");
        pd.show();
    }

    @Override
    protected void onPostExecute(String jsonData) {
        super.onPostExecute(jsonData);
        pd.dismiss();

        if(jsonData.startsWith("Error"))
        {
            Toast.makeText(c,"Unsuccessful "+jsonData,Toast.LENGTH_SHORT).show();
        }else
        {
            //PARSE
            new DataParser(c,jsonData,lv).execute();
        }
    }

    private String downloadData()
    {
        Object connection=Connector.connect(urlAddess);
        if(connection.toString().startsWith("Error"))
        {
            return connection.toString();
        }

        try {
            HttpURLConnection con= (HttpURLConnection) connection;

            InputStream is=new BufferedInputStream(con.getInputStream());
            BufferedReader br=new BufferedReader(new InputStreamReader(is));

            String line;
            StringBuffer jsonData=new StringBuffer();

            while ((line=br.readLine()) != null)
            {
                jsonData.append(line+"n");

            }

            br.close();
            is.close();

            return jsonData.toString();

        } catch (IOException e) {
            e.printStackTrace();
            return "Error "+e.getMessage();
        }

    }
}
