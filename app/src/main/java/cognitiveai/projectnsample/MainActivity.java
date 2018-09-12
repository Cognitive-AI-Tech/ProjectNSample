package cognitiveai.projectnsample;

/*
 *  Copyright (C) Cognitive AI Technologies, Inc - All Rights Reserved
 *  * Unauthorized copying of this file, via any medium is strictly prohibited
 *  * Proprietary and confidential
 *  * Written by Javy Kong, 2015
 *
 */

/*
 *  by Matthew Qiu
 */


import android.app.Activity;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

    //UI components
    Button mBtnNormal, mBtnUpdate, mBtnRemove, mBtnStop;

    //variables
    NotificationManager notificationManager;
    Bitmap bitmap;
    Notification.Builder builder_normal, builder_stop, builder_remove, builder_update;
    PendingIntent pendingIntent_normal, pendingIntent_stop, pendingIntent_remove, pendingIntent_update;
    Intent intent_normal, intent_stop, intent_remove, intent_update;

    final Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initiate Notification Manager
        notificationManager = (NotificationManager)this.getSystemService(Context.NOTIFICATION_SERVICE);

        //Initiate UI components
        mBtnNormal = findViewById(R.id.btn_normal);
        mBtnStop = findViewById(R.id.btn_stop);
        mBtnRemove = findViewById(R.id.btn_remove);
        mBtnUpdate = findViewById(R.id.btn_update);


        //Notification builder - normal

        //here, we will set a picture in notification
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_info); //set bitmap resource

        //standard notification builder
        builder_normal = new Notification.Builder(this);
        builder_normal.setSmallIcon(R.mipmap.ic_launcher);
        //set notification title to indicate Carloudy app what type of notification it is - send new info to Carloudy app
        builder_normal.setContentTitle("[normal]");
        //set notification text in JSON object format
        builder_normal.setContentText(
                "{" +
                "txt:" +
                "{" +
                "tx1:" +
                "{id:321,tx:\"Hi, this is Carloudy\",s:36,x:240,y:450,w:0,h:400}," +
                "tx2:" +
                "{id:322,tx:\"sample\",s:34,x:550,y:50,w:400,h:400}," +
                "tx3:" +
                "{id:323,tx:\"Welcome!\",s:36,x:350,y:530,w:0,h:400}" +
                "}," +
                "img:" +
                "{id:1,x:350,y:100,w:300,h:300}" +
                "}");
        builder_normal.setLargeIcon(bitmap);  //set the picture as the Large Icon resource in this notification
        intent_normal = new Intent(this, MainActivity.class);
        pendingIntent_normal = PendingIntent.getActivity(this, 0, intent_normal, PendingIntent.FLAG_UPDATE_CURRENT);
        builder_normal.setContentIntent(pendingIntent_normal);

        //Notification builder - update

        //standard notification builder
        builder_update = new Notification.Builder(this);
        builder_update.setSmallIcon(R.mipmap.ic_launcher);
        //set notification title to indicate Carloudy app what type of notification it is - update existed info
        builder_update.setContentTitle("[update]");
        //set notification text in JSON object format
        builder_update.setContentText(
                "{" +
                "txt:" +
                "{" +
                "tx1:" +
                "{id:321,tx:\"Hi, Update!\"}," +
                "tx2:" +
                "{id:322,tx:\"another update\",s:32,x:550,y:450,w:300,h:400}," +
                "tx3:" +
                "{id:323,tx:\"\"}" +
                "}," +
                "img:" +
                "{id:1}" +
                "}");
        intent_update = new Intent(this, MainActivity.class);
        intent_update.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        pendingIntent_update = PendingIntent.getActivity(this, 0, intent_update, PendingIntent.FLAG_UPDATE_CURRENT);
        builder_update.setContentIntent(pendingIntent_update);

        //Notification builder - remove
        builder_remove = new Notification.Builder(this);
        builder_remove.setSmallIcon(R.mipmap.ic_launcher);
        //set notification title to indicate Carloudy app what type of notification it is - remove all the info
        builder_remove.setContentTitle("[remove]");
        intent_remove = new Intent(this, MainActivity.class);
        intent_remove.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        pendingIntent_remove = PendingIntent.getActivity(this, 0, intent_remove, PendingIntent.FLAG_UPDATE_CURRENT);
        builder_remove.setContentIntent(pendingIntent_remove);

        //Notification builder - stop
        builder_stop = new Notification.Builder(this);
        builder_stop.setSmallIcon(R.mipmap.ic_launcher);
        //set notification title to indicate Carloudy app what type of notification it is - stop pushing info to Carloudy app
        builder_stop.setContentTitle("[stop]");
        intent_stop = new Intent(this, MainActivity.class);
        intent_stop.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        pendingIntent_stop = PendingIntent.getActivity(this, 0, intent_stop, PendingIntent.FLAG_UPDATE_CURRENT);
        builder_stop.setContentIntent(pendingIntent_stop);



        //Button listener - normal
        mBtnNormal.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                //push the notification
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O)
                {
                    int importance = NotificationManager.IMPORTANCE_HIGH;
                    NotificationChannel notificationChannel = new NotificationChannel("10001", "NOTIFICATION_CHANNEL_NAME", importance);
                    assert notificationManager != null;
                    builder_normal.setChannelId("10001");
                    notificationManager.createNotificationChannel(notificationChannel);
                }
                Notification noti = builder_normal.build();
                noti.flags |= Notification.FLAG_ONLY_ALERT_ONCE;
                assert notificationManager != null;
                notificationManager.notify(0, noti);

                //remove notification in 100ms so that the user won't be annoyed by the notification details
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        notificationManager.cancel(0);
                    }
                }, 100);
            }
        });

        //Button listener - update
        mBtnStop.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                //push the notification
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O)
                {
                    int importance = NotificationManager.IMPORTANCE_HIGH;
                    NotificationChannel notificationChannel = new NotificationChannel("10001", "NOTIFICATION_CHANNEL_NAME", importance);
                    assert notificationManager != null;
                    builder_stop.setChannelId("10001");
                    notificationManager.createNotificationChannel(notificationChannel);
                }
                assert notificationManager != null;
                notificationManager.notify(0, builder_stop.build());

                //remove notification in 100ms so that the user won't be annoyed by the notification details
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        notificationManager.cancel(0);
                    }
                }, 100);
            }
        });

        //Button listener - remove
        mBtnUpdate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                //push the notification
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O)
                {
                    int importance = NotificationManager.IMPORTANCE_HIGH;
                    NotificationChannel notificationChannel = new NotificationChannel("10001", "NOTIFICATION_CHANNEL_NAME", importance);
                    assert notificationManager != null;
                    builder_update.setChannelId("10001");
                    notificationManager.createNotificationChannel(notificationChannel);
                }
                assert notificationManager != null;
                notificationManager.notify(0, builder_update.build());

                //remove notification in 100ms so that the user won't be annoyed by the notification details
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        notificationManager.cancel(0);
                    }
                }, 100);
            }
        });

        //Button listener - stop
        mBtnRemove.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                //push the notification
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O)
                {
                    int importance = NotificationManager.IMPORTANCE_HIGH;
                    NotificationChannel notificationChannel = new NotificationChannel("10001", "NOTIFICATION_CHANNEL_NAME", importance);
                    assert notificationManager != null;
                    builder_remove.setChannelId("10001");
                    notificationManager.createNotificationChannel(notificationChannel);
                }
                assert notificationManager != null;
                notificationManager.notify(0, builder_remove.build());

                //remove notification in 100ms so that the user won't be annoyed by the notification details
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        notificationManager.cancel(0);
                    }
                }, 100);
            }
        });
    }
}
