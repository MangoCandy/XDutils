package com.hnxind.xdutils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.hnxind.object.MView.MBanner.MBanner;
import com.hnxind.object.MView.ShowImage.Act_ZoomImage;
import com.hnxind.object.MView.ShowImage.GlideLoader;
import com.hnxind.object.Picture.Adapter_PickPicture;
import com.hnxind.object.Picture.PickPictureNine;
import com.hnxind.object.Toast.MToast;
import com.yancy.imageselector.ImageConfig;
import com.yancy.imageselector.ImageSelector;
import com.yancy.imageselector.ImageSelectorActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    MBanner banner;
    Context context = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = new Intent(this,Act_ZoomImage.class);
        intent.putExtra(Act_ZoomImage.IMAGE_PATHS,new ArrayList<String>());
        startActivity(intent);
        init();
    }
    public void init(){
        banner = (MBanner)findViewById(R.id.banner);
        List<String> views = new ArrayList<>();
        for(int i = 0;i<2;i++){
            views.add("http://img5.imgtn.bdimg.com/it/u=1349121993,2318126984&fm=15&gp=0.jpg");
        }
        banner.addViews(views, new MBanner.OnItemClickListenner() {
            @Override
            public void onClick(int postion) {
                MToast.show(context,postion+"个");
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }

    int i = 0;
    @Override
    public void onBackPressed() {
        MToast.show(this,i+++"");
//        super.onBackPressed();
    }
}
