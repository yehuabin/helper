package com.yhb.taobaohelper;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import java.io.Serializable;

import cn.bmob.v3.Bmob;

/**
 * Created by Administrator on 2017/11/25.
 */

public abstract class BaseActivity extends AppCompatActivity {
    private static final String TAG = "BaseActivity";
    protected ViewHolder mViewHolder;
    private Toast mToast;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, getClass().getSimpleName());
        mViewHolder = new ViewHolder(getLayoutInflater(), null, getLayoutId());
        setContentView(mViewHolder.getRootView());
        Bmob.initialize(this, "9be40913fa1a1f940dc81aafa1139757");
    }
    @LayoutRes
    protected abstract int getLayoutId();

    public ViewHolder getViewHolder() {
        return mViewHolder;
    }

    /**
     * 发出一个短Toast
     *
     * @param text 内容
     */
    public void toastShort(String text) {
        toast(text, Toast.LENGTH_SHORT);
    }

    /**
     * 发出一个长toast提醒
     *
     * @param text 内容
     */
    public void toastLong(String text) {
        toast(text, Toast.LENGTH_LONG);
    }


    private void toast(final String text, final int duration) {
        if (!TextUtils.isEmpty(text)) {
            runOnUiThread(new Runnable() {

                @Override
                public void run() {
                    if (mToast == null) {
                        mToast = Toast.makeText(getApplicationContext(), text, duration);
                    } else {
                        mToast.setText(text);
                        mToast.setDuration(duration);
                    }
                    mToast.show();
                }
            });
        }
    }


    protected void openActivity(Class<?> cls) {
        openActivity(this, cls);
    }

    public static void openActivity(Context context, Class<?> cls) {
        Intent intent = new Intent(context, cls);
        context.startActivity(intent);
    }

    /**
     * 打开 Activity 的同时传递一个数据
     */
    protected <V extends Serializable> void openActivity(Class<?> cls, String key, V value) {
        openActivity(this, cls, key, value);
    }


    /**
     * 打开 Activity 的同时传递一个数据
     */
    public <V extends Serializable> void openActivity(Context context, Class<?> cls, String key, V value) {
        Intent intent = new Intent(context, cls);
        intent.putExtra(key, value);
        context.startActivity(intent);
    }

}
