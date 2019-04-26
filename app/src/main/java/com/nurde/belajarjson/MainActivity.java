package com.nurde.belajarjson;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {
    TextView txt_Id,txt_Nama,txt_Email;
    String id,nama,email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txt_Id = (TextView) findViewById(R.id.txt_Id);
        txt_Nama = (TextView) findViewById(R.id.txt_Nama);
        txt_Email = (TextView) findViewById(R.id.txt_Email);

        try {
            //get JSONObject from JSON file
            JSONObject obj = new JSONObject(loadJSONSFromAsset());

            //fetch JSONArray named users
            JSONArray userArray = obj.getJSONArray("users");
            for (int i = 0; i < userArray.length(); i++) {
                //create a JSONObject for  fetching single user data
                JSONObject userDetail = userArray.getJSONObject(i);

                id = userDetail.getString("id");
                nama = userDetail.getString("name");
                email = userDetail.getString("email");

                //set textView
                txt_Id.setText("ID       :   " + id);
                txt_Nama.setText("Nama       : " + nama);
                txt_Email.setText("Email     : " + email);
            }
            } catch(JSONException e){
                e.printStackTrace();
            }
        }


    public String loadJSONSFromAsset(){
        String json = null;
        try {
            InputStream is = getAssets().open("users.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer,"UTF-8");
        } catch (IOException ex){
            ex.printStackTrace();
            return null;
        }
        return json;
    }
}
