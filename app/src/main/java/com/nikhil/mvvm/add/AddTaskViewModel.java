package com.nikhil.mvvm.add;

import android.arch.lifecycle.ViewModel;
import android.os.AsyncTask;
import android.util.Log;

import com.nikhil.mvvm.data.ListItem;
import com.nikhil.mvvm.data.ListItemRepository;

/**
 * Created by nikhil on 24/12/17.
 */

public class AddTaskViewModel extends ViewModel {

    private static final String TAG = "AddTaskViewModel";
    private ListItemRepository repository;

    public AddTaskViewModel(ListItemRepository repository){
        this.repository = repository;
    }

    public void addTaskToDB(ListItem item){
        new AddTaskAsync().execute(item);
    }

    private class AddTaskAsync extends AsyncTask<ListItem, Void, Void>{
        @Override
        protected Void doInBackground(ListItem... listItems) {
            repository.insertListItem(listItems[0]);
            Log.d(TAG, "doInBackground: " + listItems[0].getMessage());
            return null;
        }
    }

}
