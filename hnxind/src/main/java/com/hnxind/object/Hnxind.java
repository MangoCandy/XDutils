package com.hnxind.object;

import android.app.Application;
import android.content.Context;

/**
 * Created by Administrator on 2016/3/15.
 */
public class Hnxind {
    public static Application application;
    public static void bind(Application c){
        application=c;
    }
}
