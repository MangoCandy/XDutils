package com.hnxind.xdutils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.hnxind.object.MView.ShowImage.GlideLoader;
import com.hnxind.object.Picture.Adapter_PickPicture;
import com.hnxind.object.Picture.PickPictureNine;
import com.hnxind.object.Toast.MToast;
import com.yancy.imageselector.ImageConfig;
import com.yancy.imageselector.ImageSelector;
import com.yancy.imageselector.ImageSelectorActivity;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    PickPictureNine pickPictureNine;
    Context context = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }
    public void init(){
        pickPictureNine = (PickPictureNine)findViewById(R.id.pick);
        pickPictureNine.setOnItemOnclickListener(new Adapter_PickPicture.OnItemOnclickListener() {
            @Override
            public void onItemClick(int position) {

            }

            @Override
            public void onPick() {
                ImageConfig imageConfig
                        = new ImageConfig.Builder(new GlideLoader())
                        .steepToolBarColor(getResources().getColor(R.color.colorPrimaryDark))
                        .titleBgColor(getResources().getColor(R.color.colorPrimary))
                        .titleSubmitTextColor(getResources().getColor(R.color.white))
                        .titleTextColor(getResources().getColor(R.color.white))
                        // 开启多选   （默认为多选）
                        .mutiSelect()
                        // 多选时的最大数量   （默认 9 张）
                        .mutiSelectMaxSize(9)
                        // 开启拍照功能 （默认关闭）
                        .showCamera()
                        // 已选择的图片路径
//                        .pathList(path)
                        // 拍照后存放的图片路径（默认 /temp/picture） （会自动创建）
                        .filePath("/zhsq/Pictures")
                        .build();
                ImageSelector.open((Activity)context,imageConfig);
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == ImageSelector.IMAGE_REQUEST_CODE){
            if(resultCode == RESULT_OK && data != null){
                List<String> pathList = data.getStringArrayListExtra(ImageSelectorActivity.EXTRA_RESULT);
                pickPictureNine.setPaths(pathList);
//                addPicture.setSrc(paths);
            }
        }
    }
}
