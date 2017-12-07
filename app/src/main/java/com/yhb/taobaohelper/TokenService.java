package com.yhb.taobaohelper;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.yhb.taobaohelper.model.LogModel;
import com.yhb.taobaohelper.utils.BmobUtil;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by smk on 2017/11/24.
 */

public class TokenService extends Service {
    private static final String TAG = "TokenService";

    @Override
    public void onCreate() {
        Log.d(TAG, "onCreate: ");
        super.onCreate();
        Resources res = getResources();
        Drawable drawable = res.getDrawable(R.drawable.logo);
//实际上这是一个BitmapDrawable对象
        BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
//可以在调用getBitmap方法，得到这个位图
        largeIcon = bitmapDrawable.getBitmap();
    }

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
                final String startTime = getTime();
                LogModel logModel = new LogModel();
                logModel.setCreator("admin");
                logModel.setModule("cookie");
                logModel.setAction("检测cookie");
                logModel.setRemark("开始时间:" + startTime);
                BmobUtil.saveLog(logModel);
                Random random = new Random();
                int sleep = (random.nextInt(56) + 5) * 10000;
                Log.d(TAG, "休息时间:" + sleep);
                while (flag) {
                    try {

                        TokenHelper.isLogin(new Callback() {
                            @Override
                            public void onFailure(Call call, IOException e) {
                                Log.d(TAG, "onFailure: ");
                            }

                            @Override
                            public void onResponse(Call call, Response response) throws IOException {
                                try {
                                    String state = "登录失败";
                                    String str = response.body().string();
                                    if (str.indexOf("yehuabin") > -1) {
                                        state = "登录成功";
                                    } else {
                                        // BmobUtil.sendSms();

                                        LogModel logModel = new LogModel();
                                        logModel.setCreator("admin");
                                        logModel.setModule("cookie");
                                        logModel.setAction("cookie检测失效");
                                        logModel.setRemark("结束时间:" + getTime());
                                        BmobUtil.saveLog(logModel);
                                        flag = false;
                                        isRunning = false;
                                    }
                                    //获取PendingIntent
                                    Intent mainIntent = new Intent(getBaseContext(), MainActivity.class);
                                    PendingIntent mainPendingIntent = PendingIntent.getActivity(getBaseContext(), 0, mainIntent, PendingIntent.FLAG_UPDATE_CURRENT);
                                    NotificationManager notifyManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                                    //实例化NotificationCompat.Builde并设置相关属性
                                    NotificationCompat.Builder builder = new NotificationCompat.Builder(getBaseContext())
                                            //设置小图标
                                            .setSmallIcon(R.drawable.logo)
                                            .setLargeIcon(largeIcon)
                                            //设置通知标题
                                            .setContentTitle(startTime)
                                            .setContentIntent(mainPendingIntent)

                                            //设置通知内容
                                            .setContentText(state);

                                    //设置通知时间，默认为系统发出通知的时间，通常不用设置
                                    //.setWhen(System.currentTimeMillis());
                                    //通过builder.build()方法生成Notification对象,并发送通知,id=1
                                    Date d = new Date();
                                    int hours = d.getHours();

                                    Notification notify = builder.build();
                                    if (state == "登录失败" && hours > 6) {
                                        builder.setDefaults(Notification.DEFAULT_ALL);
                                        notify = builder.build();
                                        notify.flags |= Notification.FLAG_INSISTENT;
                                        Log.d(TAG, "时间: " + hours);
                                    }
                                    startForeground(1, notify);

                                } catch (Exception e) {

                                }
                            }
                        });

                        Thread.sleep(sleep);

                        sleep = (random.nextInt(56) + 5) * 10000;
                        Log.d(TAG, "休息时间:" + sleep);

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
