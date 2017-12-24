package com.nikhil.mvvm.di;

import android.app.Application;

import com.nikhil.mvvm.app.AppClass;

import dagger.Module;
import dagger.Provides;

/**
 * Created by nikhil on 24/12/17.
 */

@Module
public class AppModule {

    private final AppClass application;

    public AppModule(AppClass appClass) {
        this.application = appClass;
    }

    @Provides
    AppClass provideAppClass(){return application;}

    @Provides
    Application provideApplication(){return application;}
}
