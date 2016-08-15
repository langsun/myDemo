package com.example.sun.mydemo.base;

import android.app.Application;

import com.facebook.stetho.Stetho;

/**
 * Created by sun on 16/8/12.
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //方法一
//        Stetho.initializeWithDefaults(this);
        //方法二
        Stetho.initialize(Stetho.newInitializerBuilder(this)
                .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this)).build());
    }
}
