package com.hnxind.object.Picture;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.widget.GridView;
import android.widget.ListAdapter;

import com.hnxind.object.R;
import com.hnxind.object.Toast.MToast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/5/5.
 */
public class AddPictureLayout extends GridView {
    public AddPictureLayout(Context context) {
        super(context);
    }
    Adapter_AddpitureLayout.AddPictureListener addPictureListener;
    Context context;
    Adapter_AddpitureLayout adapter;
    List<String> srcs = new ArrayList<>();
    public AddPictureLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        setNumColumns(3);
    }

    public void setAddPictureListener(Adapter_AddpitureLayout.AddPictureListener addPictureListener){
        this.addPictureListener = addPictureListener;
    }
    public void setSrc(List<String> srcs){
        this.srcs = srcs;
        adapter = new Adapter_AddpitureLayout(srcs);
        setAdapter(adapter);
        adapter.notifyDataSetChanged();

        this.adapter.setAddPictureListener(new Adapter_AddpitureLayout.AddPictureListener() {
            @Override
            public void AddPicture() {
                if(addPictureListener!=null){
                    addPictureListener.AddPicture();
                }else{
                    MToast.show(context,"请配置监听器");
                }
            }
        });
    }
    @Override
    public void setAdapter(ListAdapter adapter) {
        super.setAdapter(adapter);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
    }

    public AddPictureLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
