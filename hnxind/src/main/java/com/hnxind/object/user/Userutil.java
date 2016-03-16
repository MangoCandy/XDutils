package com.hnxind.object.user;

import com.android.volley.VolleyError;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/3/15.
 */
public class Userutil {
    Map<String,String> userinfo=new HashMap<>();
    int method;//提交方法 GET/POST
    String url;//提交地址
    OnOverListener onOverListener;
    //注册前加入信息
    public void put(String key,String value){
        userinfo.put(key, value);
    }
    public void setMethod(int method){
        this.method=method;
    }
    public void setUrl(String url){
        this.url=url;
    }
    public void setUserinfo(Map<String,String> userinfo){
        this.userinfo=userinfo;
    }
    public void setOnOverListener(OnOverListener onOverListener){
        this.onOverListener=onOverListener;
    }

    public void register(){
        Register register=new Register();
        register.post(method,url,userinfo);
        register.setOnOverListener(new Register.OnOverListener() {
            @Override
            public void onSuccess(String response) {
                onOverListener.onSuccess(response);
            }

            @Override
            public void onFailure(VolleyError error) {
                onOverListener.onFailure(error);
            }
        });
    }

    public void login(){
        Login login=new Login();
        login.post(method,url,userinfo);
        login.setOnOverListener(new Login.OnOverListener() {
            @Override
            public void onSuccess(String response) {
                onOverListener.onSuccess(response);
            }

            @Override
            public void onFailure(VolleyError error) {
                onOverListener.onFailure(error);
            }
        });
    }
    //请求结果监听
    public static interface OnOverListener{
        public void onSuccess(String response);
        public void onFailure(VolleyError error);
    }


}
