package com.hnxind.object.Contacts;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.telephony.SmsManager;
import android.widget.Toast;

import java.util.List;

/**
 * Created by Administrator on 2016/5/5.
 */
public class MSGUtil {//发送短信
    Context context;
    public MSGUtil(Context context){
        this.context = context;
    }

    BroadcastReceiver receiver;
    //短信相关
    public void initBroad() {
         receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                switch (getResultCode()) {
                    case Activity.RESULT_OK:
                        Toast.makeText(context,
                                "短信发送成功", Toast.LENGTH_SHORT)
                                .show();
                        break;
                    case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
                        Toast.makeText(context,
                                "短信发送失败", Toast.LENGTH_SHORT)
                                .show();
                        break;
                    case SmsManager.RESULT_ERROR_RADIO_OFF:
                        Toast.makeText(context,
                                "短信发送失败，请检查手机是否开启飞行模式或者已欠费", Toast.LENGTH_SHORT)
                                .show();
                        break;
                    case SmsManager.RESULT_ERROR_NULL_PDU:
                        Toast.makeText(context,
                                "短信发送失败", Toast.LENGTH_SHORT)
                                .show();
                        break;
                }
                unregister();
            }
        };
        context.registerReceiver(receiver, new IntentFilter("SENT_SMS_ACTION"));
    }

    public void unregister(){
        context.unregisterReceiver(receiver);
    }
    public void sendMsg(String num,String msg){
        Intent sentIntent = new Intent("SENT_SMS_ACTION");
        Intent deliverIntent = new Intent("DELIVERED_SMS_ACTION");
        PendingIntent sentPI = PendingIntent.getBroadcast(context, 0, sentIntent, 0);
        PendingIntent deliverPI = PendingIntent.getBroadcast(context, 0,
                deliverIntent, 0);
        String content=null;
        initBroad();
        if(msg.equals("")){
            Toast.makeText(context,"请输入短信内容",Toast.LENGTH_SHORT).show();
        }else{
            content=msg;
        }

        android.telephony.SmsManager smsManager = android.telephony.SmsManager.getDefault();
        java.util.List<String> texts = smsManager.divideMessage(content);
//发送之前检查短信内容是否为空
        for (String text :texts) {
            smsManager.sendTextMessage(num, null, text, sentPI, deliverPI);
        }
    }

    public void sendMsg(List<String> nums, String msg){
        Intent sentIntent = new Intent("SENT_SMS_ACTION");
        Intent deliverIntent = new Intent("DELIVERED_SMS_ACTION");
        PendingIntent sentPI = PendingIntent.getBroadcast(context, 0, sentIntent, 0);
        PendingIntent deliverPI = PendingIntent.getBroadcast(context, 0,
                deliverIntent, 0);
        String content=null;
        initBroad();
        if(msg.equals("")){
            Toast.makeText(context,"请输入短信内容",Toast.LENGTH_SHORT).show();
        }else{
            content=msg;
        }
        for(String num:nums){
            android.telephony.SmsManager smsManager = android.telephony.SmsManager.getDefault();
            java.util.List<String> texts = smsManager.divideMessage(content);
//发送之前检查短信内容是否为空
            for (String text :texts) {
                smsManager.sendTextMessage(num, null, text, sentPI, deliverPI);
            }
        }
    }
}
