package com.hnxind.object.Toast;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Administrator on 2016/5/5.
 */
public class MToast {
    static Toast toast;
    public static void show(Context context,String content){
        if(toast!=null){toast.cancel();}
        toast = Toast.makeText(context,content,Toast.LENGTH_SHORT);
        toast.show();
//        Toast.makeText(context,content,Toast.LENGTH_SHORT).show();
    }

    public static void show(Context context,int s){
        if(toast!=null){toast.cancel();}
        toast = Toast.makeText(context,context.getResources().getString(s),Toast.LENGTH_SHORT);
        toast.show();
    }

    public static void show(Context context,String content,int time){
        if(toast!=null){toast.cancel();}
        toast = Toast.makeText(context,content,time);
        toast.show();
    }
}
