package com.hnxind.object.Notification;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.MailTo;
import android.os.Build;
import android.support.v7.app.NotificationCompat;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * Created by Administrator on 2016/4/12.
 */
public class MNotification {
    Context context;
    NotificationManager manager;
    public MNotification (Context context){
        this.context = context;
        manager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
    }

    String contenttext,ticker,contenttitle="";
    int smallicon,number,id=-1;

    public void setId(int id){
        this.id = id;
    }
    public void setTittle(String contenttitle){
        this.contenttitle = contenttitle;
    }

    public void setTicker(String ticker){
        this.ticker = ticker;
    }

    public void setContenttext(String contenttext){
        this.contenttext = contenttext;
    }

    public void setSmallicon(int smallicon){
        this.smallicon = smallicon;
    }

    public void setNumber(int number){
        this.number = number;
    }
    public void show(){
        Intent intent=new Intent();
        PendingIntent intents =PendingIntent.getService(context,id,intent,0);
        NotificationCompat.Builder notification=new NotificationCompat.Builder(context);
        notification.setContentText(contenttext);
        notification.setSmallIcon(smallicon);
        notification.setTicker(contenttext);
        notification.setContentTitle(contenttitle);
        notification.setNumber(number);
        notification.setContentIntent(intents);
        notification.setLargeIcon(BitmapFactory.decodeResource(context.getResources(), smallicon));
        manager.notify(id==-1?-1:id,notification.build());
    }

    public void show(NotificationCompat.Builder notification){
        manager.notify(id==-1?-1:id,notification.build());
    }
}
