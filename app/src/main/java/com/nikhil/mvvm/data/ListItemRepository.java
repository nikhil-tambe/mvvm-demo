package com.nikhil.mvvm.data;

import android.arch.lifecycle.LiveData;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by nikhil on 24/12/17.
 */

public class ListItemRepository {


    private final ListItemDAO listItemDAO;

    @Inject
    public ListItemRepository(ListItemDAO listItemDAO){
        this.listItemDAO = listItemDAO;
    }

    public void insertListItem(ListItem listItem){
        listItemDAO.insertListItem(listItem);
    }

    public void deleteListItem(ListItem listItem){
        listItemDAO.deleteListItem(listItem);
    }

    public LiveData<List<ListItem>> getListOfData() {
        return listItemDAO.getListOfData();
    }

    public LiveData<ListItem> getListItem(String id){
        return listItemDAO.getItemByID(id);
    }

}
