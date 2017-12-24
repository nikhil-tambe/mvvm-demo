package com.nikhil.mvvm.app;

import android.app.Application;

import com.nikhil.mvvm.di.AppComponent;
import com.nikhil.mvvm.di.AppModule;
import com.nikhil.mvvm.di.DaggerAppComponent;
import com.nikhil.mvvm.di.RoomModule;

/**
 * Created by nikhil on 24/12/17.
 */

public class AppClass extends Application {

    private AppComponent component;

    @Override
    public void onCreate() {
        super.onCreate();

        component = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .roomModule(new RoomModule(this))
                .build();
    }

    public AppComponent getAppComponent() {
        return component;
    }
}
