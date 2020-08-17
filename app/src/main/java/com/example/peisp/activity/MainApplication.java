package com.example.peisp.activity;

import android.app.Application;

import com.fengmap.android.FMMapSDK;

/**
 * 应用层初始化
 *
 */
public class MainApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        // 在使用 SDK 各组间之前初始化 context 信息，传入 Application
        FMMapSDK.init(this);
    }

}