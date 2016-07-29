package com.hnxind.object.MView.ShowImage;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.hnxind.object.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class Act_ZoomImage extends AppCompatActivity {
    ViewPager viewPager;
    List<String> pathList = new ArrayList<>();
    Adapter_zoomimage adapterZoomimage;
    static public String IMAGE_PATHS = "imagepath";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zoom_image);
        ButterKnife.bind(this);
        pathList = (List<String>) getIntent().getSerializableExtra(IMAGE_PATHS);
        initView();
    }



    public void initView(){
        viewPager = (ViewPager)findViewById(R.id.viewPager);
        adapterZoomimage = new Adapter_zoomimage(pathList);
        viewPager.setAdapter(adapterZoomimage);
    }
}
