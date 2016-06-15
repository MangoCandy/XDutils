package com.hnxind.object.MView;

import android.content.Context;
import android.view.inputmethod.InputMethodManager;

/**
 * Created by Administrator on 2016/6/15.
 */
public class SoftinputUtils {
    public static void  showSoftInput(Context context){
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        if(!softinputisActive(context)){
            imm.toggleSoftInput(0, InputMethodManager.RESULT_SHOWN);
        }
    }
    public static void  hideSoftInput(Context context){
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        if(softinputisActive(context)){
            imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    public static boolean softinputisActive(Context context){
        InputMethodManager imm = (InputMethodManager)context.getSystemService(Context.INPUT_METHOD_SERVICE);
        boolean isOpen=imm.isActive();//isOpen若返回true，则表示输入法打开
        return isOpen;
    }
}
