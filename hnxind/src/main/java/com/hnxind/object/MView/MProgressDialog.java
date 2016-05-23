package com.hnxind.object.MView;

import android.app.ProgressDialog;
import android.content.Context;

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

    public static void dismiss(){
        if(progressDialog!=null&&progressDialog.isShowing()){
            progressDialog.dismiss();
        }
    }
}
