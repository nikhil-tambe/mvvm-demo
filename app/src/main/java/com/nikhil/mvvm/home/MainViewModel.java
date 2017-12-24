package com.nikhil.mvvm.home;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.os.AsyncTask;

import com.nikhil.mvvm.data.ListItem;
import com.nikhil.mvvm.data.ListItemRepository;

import java.util.List;

/**
 * Created by nikhil on 24/12/17.
 */

public class MainViewModel extends ViewModel {

    private ListItemRepository repository;

    public MainViewModel(ListItemRepository repository) {
        this.repository = repository;
    }

    public LiveData<List<ListItem>> getListOfData(){
        return repository.getListOfData();
    }

    public void deleteTask(ListItem listItem){
        new DeleteTaskAsync().execute(listItem);
    }

    private class DeleteTaskAsync extends AsyncTask<ListItem, Void, Void>{
        @Override
        protected Void doInBackground(ListItem... listItems) {
            repository.deleteListItem(listItems[0]);
            return null;
        }
    }
}
