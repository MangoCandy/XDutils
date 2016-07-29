package com.hnxind.object.MView.ShowImage;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.hnxind.object.R;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Act_ShowImage extends AppCompatActivity {
    Context context = this;
    Bitmap bitmap;
    String url;
    List<String> paths;
    public static String BITMAP = "bitmap";
    public static String PATHS = "paths";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_image);
        initBitmap();
    }

    private void initBitmap() {
        url = getIntent().getExtras().getString("bitmap");
        if(url!=null){
            getBitMap(url);
//            Thread thread = new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    try {
//                        bitmap = Picasso.with(context).load(url).get();
//                        handler.sendEmptyMessage(0);
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
//            });
//            thread.start();
        }
        paths = getIntent().getExtras().getStringArrayList(PATHS);
        if(paths!=null){
            getBitmaps();
        }
    }
    List<Bitmap> bitmaps = new ArrayList<>();
    public void getBitmaps(){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    bitmaps.clear();
                    for(String path:paths){
                        bitmap = Picasso.with(context).load(path).get();
                        bitmaps.add(bitmap);
                    }
                    handler.sendEmptyMessage(1);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }
    public void getBitMap(String path){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    bitmap = Picasso.with(context).load(url).get();
                    handler.sendEmptyMessage(1);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 0:
                    initViewList(bitmaps);
                    break;
                case 1:
                    initView();
                    break;
            }
        }
    };
    public void initViewList(List<Bitmap> bitmaps){
        galleryView = (ScrollGalleryView)findViewById(R.id.gallery);
        galleryView.setZoom(true);
        galleryView.setFragmentManager(getSupportFragmentManager());
        galleryView.setThumbnailSize(80);
        for(Bitmap bitmap:bitmaps){
            galleryView.addMedia(MediaInfo.mediaLoader(new DefaultImageLoader(bitmap)));
        }

    }
    public void initView(){
        galleryView = (ScrollGalleryView)findViewById(R.id.gallery);
        galleryView.setZoom(true);
        galleryView.setFragmentManager(getSupportFragmentManager());
        galleryView.hideThumbnails(true);
        galleryView.setThumbnailSize(1);
        if(url!=null){
            galleryView.addMedia(MediaInfo.mediaLoader(new DefaultImageLoader(bitmap)));

        }
    }
}
