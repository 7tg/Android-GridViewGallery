package com.tayyipgoren.galleypage;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.widget.GridView;

public class MainActivity extends AppCompatActivity {
    public static GridView gridView;
    public static ImageAdapter imageAdapter;

    public static String LOG_TAG = "GalleryApp";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gridView = findViewById(R.id.grid_view);

        // Setting image adapter
        this.imageAdapter = new ImageAdapter(this);
        gridView.setAdapter(imageAdapter);

        // Starting api thread
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ApiHandler api = new ApiHandler();
                api.execute("https://api.myjson.com/bins/16xcic");
            }
        });




    }

}
