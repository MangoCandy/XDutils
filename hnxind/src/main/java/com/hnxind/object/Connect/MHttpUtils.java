package com.hnxind.object.Connect;


import android.os.Looper;
import android.util.Log;

import com.hnxind.object.Toast.MToast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Administrator on 2016/5/19.
 */
public class MHttpUtils {
    FormBody formBody;
    OkHttpClient client;
    Response response;
    String URL;
    OnSuccessListener onSuccessListener;
    OnFailueListener onFailueListener;

    public MHttpUtils(String URL,OnSuccessListener onSuccessListener,OnFailueListener onFailueListener){
        client = new OkHttpClient();

        this.URL = URL;
        this.onSuccessListener = onSuccessListener;
        this.onFailueListener = onFailueListener;

    }

    public void addPost(Map<String,String> params){
        FormBody.Builder builder = new FormBody.Builder();
        Set keyset = params.keySet();
        String[] p = new String[keyset.size()];
        List<String> values = new ArrayList<>();
        for (Iterator i = keyset.iterator(); i.hasNext();)
        {
            String key = (String) i.next();
            String value = params.get(key);
            builder.add(key,value);
        }
        formBody = builder.build();
    }

    public void start(){
        Request.Builder builder = new Request.Builder();
        builder.url(URL);
        if(formBody!=null){
            builder.post(formBody);
        }
        final Request request = builder
                .build();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Call call = client.newCall(request);
                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        onFail(e);
                    }

                    @Override
                    public void onResponse(Call call, Response r) throws IOException {
                        onSuccess(r);
                    }
                });
            }
        });
        thread.start();
    }

    private void onFail(IOException e){
        Looper.prepare();
        onFailueListener.onFailue(e.getMessage());
        Looper.loop();
    }

    private void onSuccess(Response r ) throws IOException {
        Looper.prepare();
        this.response = r;
        if(response.isSuccessful()){
            onSuccessListener.onSuccess(response.body().string());
        }else{
            onFailueListener.onFailue("错误编码:"+response.code());
        }
        Looper.loop();
    }

    public interface OnSuccessListener{
        public void onSuccess(String content);
    }

    public interface OnFailueListener{
        public void onFailue(String content);
    }
}
