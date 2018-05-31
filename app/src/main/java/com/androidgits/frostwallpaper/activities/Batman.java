package com.androidgits.frostwallpaper.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.androidgits.frostwallpaper.R;
import com.androidgits.frostwallpaper.adapter.BatmanAdapter;
import com.androidgits.frostwallpaper.adapter.WallAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Batman extends AppCompatActivity {

    private RecyclerView batmanRecyclerView;
    private JsonArrayRequest ArrayRequest;
    private RequestQueue requestQueue;
    //private String URL_JSON = "https://firebasestorage.googleapis.com/v0/b/frost-wallpaper.appspot.com/o/Joker%2FJoker.json?alt=media&token=1eca6298-d1fd-4fe4-8e6e-83bc23d3abc5";
    private String URL_JSON;
    private List<com.androidgits.frostwallpaper.model.Batman> lstBatman = new ArrayList<>();

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_batman);

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Take Some Rest");
        progressDialog.setMessage("Wallpaper Is Loading ...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        batmanRecyclerView = (RecyclerView) findViewById(R.id.batmanRecyclerView);
        Intent intent = getIntent();
        int Id = intent.getIntExtra("Id",0);
        if ( Id == 0){
        URL_JSON = "https://firebasestorage.googleapis.com/v0/b/frost-wallpaper.appspot.com/o/Batman%2Fbatman.json?alt=media&token=0eae0b4c-fe44-4196-857f-621c99267aa2";
        }
        else if (Id == 1){
            URL_JSON = "https://firebasestorage.googleapis.com/v0/b/frost-wallpaper.appspot.com/o/Captain%20America%2FCaptainAmerica.json?alt=media&token=27890329-bdc9-4993-85eb-24f1f8e0320e";
        }
        else if (Id == 2){
            URL_JSON = "https://firebasestorage.googleapis.com/v0/b/frost-wallpaper.appspot.com/o/Dead%20Pool%2Fdead%20pool.json?alt=media&token=14204823-9851-41d5-8536-1d3bfde1e84e";
        }
        else if (Id == 3){
            URL_JSON = "https://firebasestorage.googleapis.com/v0/b/frost-wallpaper.appspot.com/o/Doctor%20Strange%2FDoctorStrange.json?alt=media&token=55f0ad96-f340-4953-a361-3473b34d1299";
        }
        else if (Id == 4){
            URL_JSON = "https://firebasestorage.googleapis.com/v0/b/frost-wallpaper.appspot.com/o/Thanos%2FThanos.json?alt=media&token=6929438d-984b-4565-8580-46720681f07c";
        }
        else if (Id == 5){
            URL_JSON = "https://firebasestorage.googleapis.com/v0/b/frost-wallpaper.appspot.com/o/Joker%2FJoker.json?alt=media&token=1eca6298-d1fd-4fe4-8e6e-83bc23d3abc5";
        }
        jsonCall();

    }

    public void jsonCall() {


        ArrayRequest = new JsonArrayRequest(URL_JSON, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
                for (int i = 0; i < response.length(); i++) {
                    try {
                        jsonObject = response.getJSONObject(i);
                        com.androidgits.frostwallpaper.model.Batman batman = new com.androidgits.frostwallpaper.model.Batman();
                        batman.setImage_Url(jsonObject.getString("Image_Url"));
                        lstBatman.add(batman);
                        progressDialog.dismiss();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                setBatmanAdapter(lstBatman);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Batman.this, "Error Occured", Toast.LENGTH_SHORT).show();

            }
        });

        requestQueue = Volley.newRequestQueue(Batman.this);
        requestQueue.add(ArrayRequest);

    }

    private void setBatmanAdapter(List<com.androidgits.frostwallpaper.model.Batman> lstBatman) {
        BatmanAdapter myAdapter = new BatmanAdapter(this,lstBatman);
       batmanRecyclerView.setLayoutManager(new GridLayoutManager(this,2));
        batmanRecyclerView.setAdapter(myAdapter);
    }
}
