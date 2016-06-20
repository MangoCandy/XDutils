package com.hnxind.object.Picture;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/6/16.
 */
public class PickPictureNine extends RecyclerView {
    List<String> paths = new ArrayList<>();
    Adapter_PickPicture adapterPickPicture;
    Adapter_PickPicture.OnItemOnclickListener onItemOnclickListener;

    public PickPictureNine(Context context) {
        super(context);
    }

    public void setPaths(List<String> paths) {
        this.paths = paths;
        init();
    }
    public void init(){
        adapterPickPicture = new Adapter_PickPicture(paths);
        setAdapter(adapterPickPicture);
        adapterPickPicture.setOnItemOnclickListener(new Adapter_PickPicture.OnItemOnclickListener() {
            @Override
            public void onItemClick(int position) {
                onItemOnclickListener.onItemClick(position);
            }

            @Override
            public void onPick() {
                onItemOnclickListener.onPick();
            }
        });
    }
    public PickPictureNine(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setLayoutManager(new GridLayoutManager(context,3));
        init();
    }

    public void addImage(String path){
        paths.add(path);
    }

    public void clearImages(){
        paths.clear();
    }

    public PickPictureNine(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void setOnItemOnclickListener(Adapter_PickPicture.OnItemOnclickListener onItemOnclickListener){
        this.onItemOnclickListener = onItemOnclickListener;
    }
}

//    public PickPictureNine(Context context, @Nullable AttributeSet attrs, int defStyle) {
//
//
//}
