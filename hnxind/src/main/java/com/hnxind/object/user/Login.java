package com.hnxind.object.user;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.hnxind.object.Hnxind;

import java.util.Map;

/**
 * Created by Administrator on 2016/3/16.
 */
public class Login {
    Context context= Hnxind.application;

    OnOverListener onOverListener;
    public void setOnOverListener(OnOverListener onOverListener){
        this.onOverListener=onOverListener;
    }

    public void post(int method, String url, final Map<String,String> map){
        StringRequest stringRequest=new StringRequest(method,url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                onOverListener.onSuccess(response);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                onOverListener.onFailure(error);
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return map;
            }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }

    public static interface OnOverListener{
        public void onSuccess(String response);
        public void onFailure(VolleyError error);
    }
}
