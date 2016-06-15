package com.hnxind.xdutils;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i("asd", Environment.getDataDirectory().getPath());
        Log.i("asd", Environment.getExternalStorageDirectory().getPath());
    }
}
