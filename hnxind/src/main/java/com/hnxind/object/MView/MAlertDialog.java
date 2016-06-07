package com.hnxind.object.MView;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

import com.hnxind.object.Toast.MToast;

/**
 * Created by Administrator on 2016/6/6.
 */
public abstract class MAlertDialog {
    Context context;
    String message="";
    String title = "";

    public MAlertDialog(Context context){
        this.context = context;
    }
    public void setMessage(String title,String message){
        this.message = message;
        this.title = title;
    }
    public void show(){
        AlertDialog alertDialog = new AlertDialog.Builder(context)
                .setMessage(message)
                .setTitle(title)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        onQueren();
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        onQuxiao();
                    }
                })
                .show();
    }
    public abstract void onQueren();
    public abstract void onQuxiao();

}
