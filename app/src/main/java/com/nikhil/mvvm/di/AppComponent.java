package com.nikhil.mvvm.di;

import android.app.Application;

import com.nikhil.mvvm.add.AddTaskFragment;
import com.nikhil.mvvm.details.DetailsFragment;
import com.nikhil.mvvm.home.MainFragment;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by nikhil on 24/12/17.
 */

@Singleton
@Component(modules = {AppModule.class, RoomModule.class})
public interface AppComponent {

    void inject(MainFragment mainFragment);

    void inject(AddTaskFragment addTaskFragment);

    void inject(DetailsFragment detailsFragment);

}
