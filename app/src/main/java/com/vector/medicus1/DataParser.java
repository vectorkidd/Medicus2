package com.vector.medicus1;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Khalid on 3/13/2018.
 */

public class DataParser extends AsyncTask<Void,Void,Boolean>{

        Context c;
        String jsonData;
        ListView lv;

        ProgressDialog pd;
        ArrayList<String> doctors=new ArrayList<>();

    public DataParser(Context c, String jsonData, ListView lv) {
            this.c = c;
            this.jsonData = jsonData;
            this.lv = lv;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            pd=new ProgressDialog(c);
            pd.setTitle("Parse");
            pd.setMessage("Pasring..Please wait");
            pd.show();
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            return this.parseData();
        }

    @Override
    protected void onPostExecute(Boolean result) {
        super.onPostExecute(result);

        pd.dismiss();
        if (result) {
            ArrayAdapter adapter = new ArrayAdapter(c, android.R.layout.simple_list_item_1, doctors);
            lv.setAdapter(adapter);

            lv.setOnItemClickListener(
                    new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Toast.makeText(c, doctors.get(position), Toast.LENGTH_SHORT).show();
                    //Intent in=new Intent(this,PopUp.class);


                }
            });
        }
    }

    private Boolean parseData() {
        try
        {
            JSONArray ja = new JSONArray(jsonData);
            JSONObject jo;

            doctors.clear();

            for (int i = 0; i < ja.length(); i++) {

                jo = ja.getJSONObject(i);

                String name = jo.getString("Dfullname");
                /*String dob=jo.getString("Ddob");
                String gender=jo.getString("Dgender");
                String speciality=jo.getString("Dspeciality");
                String hospital=jo.getString("Dhospital");
                String linum=jo.getString("Dlinum");*/


                doctors.add(name);
                /*doctors.add(dob);
                doctors.add(gender);
                doctors.add(speciality);
                doctors.add(hospital);
                doctors.add(linum);*/
            }

            return true;

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return false;
    }

    }




