package com.nikhil.mvvm.details;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.nikhil.mvvm.data.ListItem;
import com.nikhil.mvvm.data.ListItemRepository;

/**
 * Created by nikhil on 24/12/17.
 */

public class DetailsViewModel extends ViewModel {

    private ListItemRepository repository;

    public DetailsViewModel(ListItemRepository repository){
        this.repository = repository;
    }

    public LiveData<ListItem> getTaskById(String id){
        return repository.getListItem(id);
    }

}
