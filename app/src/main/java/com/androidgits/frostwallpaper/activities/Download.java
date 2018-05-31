package com.androidgits.frostwallpaper.activities;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.androidgits.frostwallpaper.R;
import com.squareup.picasso.Picasso;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class Download extends AppCompatActivity {
private ImageView imageView;
    // image url
    String geturl;
Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download);

        imageView = (ImageView) findViewById(R.id.imageView);
        // here we have the url of image which we have to downlaod
        geturl = getIntent().getExtras().getString("ImageUrl");
        // load image from url
        Picasso.with(getApplicationContext())
                .load(geturl)
                .into(imageView);

        button = (Button) findViewById(R.id.downloadBtn);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DownloadTask downloadTask = new DownloadTask();
                downloadTask.execute(geturl);
            }
        });

    }

    class DownloadTask extends AsyncTask<String,Integer,String>
    {
        ProgressDialog progressDialog;


        @Override
        protected void onPreExecute() {
            progressDialog = new ProgressDialog(Download.this);
            progressDialog.setTitle("Download In Progress");
            progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            progressDialog.setMax(100);
            progressDialog.setProgress(0);
            progressDialog.show();
        }

        @Override
        protected String doInBackground(String... params) {
            String path = params[0];
            int file_length = 0;
            try {
                URL url = new URL(path);
                URLConnection urlConnection = url.openConnection();
                urlConnection.connect();
                file_length = urlConnection.getContentLength();
                File frost_wallpaper = new File("sdcard/photoalbum");

                if (!frost_wallpaper.exists()){
                    frost_wallpaper.mkdir();
                }

                File input_file = new File(frost_wallpaper,"downloaded_image.jpg");
                InputStream inputStream = new BufferedInputStream(url.openStream(),8192);
                byte[] data = new byte[1024];
                int total = 0;
                int count = 0;

                OutputStream outputStream = new FileOutputStream(input_file);
                while ((count=inputStream.read(data))!=-1){
                    total+=count;
                    outputStream.write(data,0,count);
                    int progress = (Integer) total*100/file_length;
                    publishProgress(progress);
                }

                inputStream.close();
                outputStream.close();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return "Download Completed";
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            progressDialog.setProgress(values[0]);
        }

        @Override
        protected void onPostExecute(String result) {
            progressDialog.hide();
            Toast.makeText(Download.this,result, Toast.LENGTH_SHORT).show();
//
//            String path = "sdcard/photoalbum/downloaded_image.jpg";
//            imageView.setImageDrawable(Drawable.createFromPath(path));
        }
    }
}