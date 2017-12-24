package com.nikhil.mvvm.di;

import android.app.Application;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;

import com.nikhil.mvvm.app.AppClass;
import com.nikhil.mvvm.app.CustomViewModelFactory;
import com.nikhil.mvvm.data.ListItem;
import com.nikhil.mvvm.data.ListItemDAO;
import com.nikhil.mvvm.data.ListItemDatabase;
import com.nikhil.mvvm.data.ListItemRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

import static com.nikhil.mvvm.app.Constants.DatabaseConstants.DB_NAME;

/**
 * Created by nikhil on 24/12/17.
 */

@Module
public class RoomModule {

    ListItemDatabase database;

    public RoomModule(AppClass appClass) {
        this.database = Room.databaseBuilder(
                appClass,
                ListItemDatabase.class,
                DB_NAME
        ).build();
    }

    @Provides
    @Singleton
    ListItemRepository provideListItemRepository(ListItemDAO dao){
        return new ListItemRepository(dao);
    }

    @Provides
    @Singleton
    ListItemDAO provideListItemDAO(ListItemDatabase database){
        return database.listItemDAO();
    }

    @Provides
    @Singleton
    ListItemDatabase provideListItemDatabase(){
        return database;
    }

    @Provides
    @Singleton
    ViewModelProvider.Factory provideViewModelProviderFactory(ListItemRepository repository){
        return new CustomViewModelFactory(repository);
    }
}
