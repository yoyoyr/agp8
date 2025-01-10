package com.asm.agp8.app;

import android.app.Application;
import android.content.Context;


public class App extends Application {


    public static Context appContext;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        appContext = base;
    }
}
