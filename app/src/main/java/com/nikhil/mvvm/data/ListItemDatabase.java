package com.nikhil.mvvm.data;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import static com.nikhil.mvvm.app.Constants.DatabaseConstants.DB_VERSION;

/**
 * Created by nikhil on 24/12/17.
 */

@Database(entities = {ListItem.class}, version = DB_VERSION, exportSchema = false)
public abstract class ListItemDatabase extends RoomDatabase {

    public abstract ListItemDAO listItemDAO();
}
