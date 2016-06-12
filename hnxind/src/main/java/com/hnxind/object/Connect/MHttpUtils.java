package com.hnxind.object.Connect;


import android.os.Looper;
import android.provider.MediaStore;
import android.util.Log;

import com.hnxind.object.Toast.MToast;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.activation.MimeType;
import javax.activation.MimetypesFileTypeMap;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by Administrator on 2016/5/19.
 */
public class MHttpUtils {
    FormBody formBody;
    RequestBody requestBody;
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

//    public void addPost(Map<String,String> params){
//        FormBody.Builder builder = new FormBody.Builder();
//        Set keyset = params.keySet();
//        for (Iterator i = keyset.iterator(); i.hasNext();)
//        {
//            String key = (String) i.next();
//            String value = params.get(key);
//            builder.add(key,value);
//        }
//        requestBody = builder.build();
//    }
//
    public void addPost(Map<String,String> params){
        MultipartBody.Builder  builder = new MultipartBody.Builder();
                builder.setType(MultipartBody.FORM);
        Set keyset = params.keySet();
        for (Iterator i = keyset.iterator(); i.hasNext();)
        {
            String key = (String) i.next();
            String value = params.get(key);
            builder.addPart(Headers.of(

                "Content-Disposition",

                "form-data; name=\""+key+"\""),

                RequestBody.create(null, value))
                .build();
        }
        requestBody = builder.build();
    }
    public void addPost(Map<String,String> params, List<String> filespath,String filekey){
        MultipartBody.Builder  builder = new MultipartBody.Builder();
        builder.setType(MultipartBody.FORM);
        Set keyset = params.keySet();
        for (Iterator i = keyset.iterator(); i.hasNext();)
        {
            String key = (String) i.next();
            String value = params.get(key);
            builder.addPart(Headers.of(

                    "Content-Disposition",

                    "form-data; name=\""+key+"\""),

                    RequestBody.create(null, value))
                    .build();
        }
        builder.addPart(Headers.of(

                "Content-Disposition",

                "form-data; name=\"count\""),

                RequestBody.create(null, filespath.size()+""))
                .build();
        for(int i = 1;i<=filespath.size();i++){
            File file = new File(filespath.get(i-1));
            builder.addFormDataPart(filekey+i,"",RequestBody.create(MediaType.parse(new MimetypesFileTypeMap().getContentType(file)),file));
        }
        requestBody = builder.build();
    }

    public void start(){
        Request.Builder builder = new Request.Builder();
        builder.url(URL);
        if(requestBody!=null){
            builder.post(requestBody);
        }
//        if(requestBody!=null){
//            builder.post(requestBody);
//        }
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
