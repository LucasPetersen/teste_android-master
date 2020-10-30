package com.salcisne.androidteste;

import android.app.Application;

import com.salcisne.androidteste.service.api.ApiModule;

public class AndroidTesteApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        new ApiModule("http://10.0.2.2:8080");
    }
}
