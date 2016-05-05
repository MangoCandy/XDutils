package com.hnxind.object.Toast;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Administrator on 2016/5/5.
 */
public class MToast {
    public static void show(Context context,String content){
        Toast.makeText(context,content,Toast.LENGTH_SHORT).show();
    }

    public static void show(Context context,String content,int time){
        Toast.makeText(context,content,time).show();
    }
}
