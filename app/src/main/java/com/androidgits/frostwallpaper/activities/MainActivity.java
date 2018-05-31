package com.androidgits.frostwallpaper.activities;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.androidgits.frostwallpaper.R;
import com.androidgits.frostwallpaper.adapter.WallAdapter;
import com.androidgits.frostwallpaper.model.Wall;
import com.facebook.shimmer.ShimmerFrameLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private String URL_JSON = "https://firebasestorage.googleapis.com/v0/b/frost-wallpaper.appspot.com/o/Wall.json?alt=media&token=7e357da2-4bfa-4012-9eeb-4cdf4dd03b56";
    private JsonArrayRequest ArrayRequest;
    private RequestQueue requestQueue;
    private List<Wall> lstWall = new ArrayList<>();

    ProgressDialog progress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // for progress bar
        progress = new ProgressDialog(this);
        progress.setTitle("Take Some Rest");
        progress.setMessage("Wallpaper Is Loading ...");
        progress.setCancelable(false);
        progress.show();

        // for progress bar


        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        jsoncall();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Developed By Tushar Verma (AndroidGits)", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    private void jsoncall() {

        ArrayRequest = new JsonArrayRequest(URL_JSON, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                JSONObject jsonObject = null;
                for (int i = 0; i < response.length(); i++) {
                    try {
                        jsonObject = response.getJSONObject(i);
                        Wall wall = new Wall();
                        wall.setBig_Image(jsonObject.getString("Big_Image"));
                        wall.setName(jsonObject.getString("Name"));
                        wall.setId(jsonObject.getInt("Id"));
                        lstWall.add(wall);
                        progress.dismiss();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
                setWallAdapter(lstWall);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "Erro Occured", Toast.LENGTH_SHORT).show();
            }
        });

        requestQueue = Volley.newRequestQueue(MainActivity.this);
        requestQueue.add(ArrayRequest);

    }

    private void setWallAdapter(List<Wall> lstWall) {
        WallAdapter myAdapter = new WallAdapter(this,lstWall);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        recyclerView.setAdapter(myAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
