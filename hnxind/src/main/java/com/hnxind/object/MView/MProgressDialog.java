package com.hnxind.object.MView;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.inputmethodservice.Keyboard;
import android.view.KeyEvent;

/**
 * Created by Administrator on 2016/5/19.
 */
public class MProgressDialog {

    public static ProgressDialog progressDialog;
    public static void show(Context context,String msg){
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage(msg);
        progressDialog.show();
    }

    public static void show(Context context,int msg){
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage(context.getResources().getString(msg));
        progressDialog.show();
    }

    public static void show(Context context,String msg,boolean cancelable){
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage(msg);
        progressDialog.setCancelable(cancelable);
        progressDialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                if(keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN){
                    progressDialog.dismiss();
                }
                return false;
            }
        });
        progressDialog.show();
    }
    public static void dismiss(){
        if(progressDialog!=null&&progressDialog.isShowing()){
            progressDialog.dismiss();
        }
    }

    public static boolean isShowing(){
        return progressDialog.isShowing();
    }
}
