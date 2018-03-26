package com.yhb.taobaohelper.services;

import android.app.Service;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.yhb.taobaohelper.utils.MYHUtil;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by smk on 2018/3/26.
 */

public class HandleTaoTokenService extends Service {
    private static final String TAG = "HandleTaoTokenService";


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    boolean flag = true;
    Bitmap largeIcon;
    static boolean isRunning = false;

    @Override
    public int onStartCommand(final Intent intent, int flags, int startId) {
        if (isRunning) {
            return super.onStartCommand(intent, flags, startId);
        }
        isRunning = true;
        new Thread(new Runnable() {
            @Override
            public void run() {
                // int i = 0;

                int sleep = 1000;

                while (true) {
                    try {


                        MYHUtil.handleTaoToken();

                        Thread.sleep(sleep);


                    } catch (Exception e) {
                        Log.d(TAG, "Exception: " + e.getMessage());
                    }
                }
            }
        }).start();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy: ");
        super.onDestroy();
    }

    public String getTime() {
        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");//可以方便地修改日期格式
        String time = dateFormat.format(now);
        return time;
    }
}
