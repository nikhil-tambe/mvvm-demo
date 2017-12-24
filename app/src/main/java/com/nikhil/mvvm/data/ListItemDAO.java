package com.nikhil.mvvm.data;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * Created by nikhil on 24/12/17.
 */

@Dao
public interface ListItemDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Long insertListItem(ListItem listItem);

    @Delete
    void deleteListItem(ListItem listItem);

    @Query("SELECT * FROM ListItem")
    LiveData<List<ListItem>> getListOfData();

    @Query("SELECT * FROM ListItem WHERE dateTime = :id")
    LiveData<ListItem> getItemByID(String id);


}
