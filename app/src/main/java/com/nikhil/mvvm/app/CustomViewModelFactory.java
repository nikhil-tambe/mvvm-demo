package com.nikhil.mvvm.app;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.nikhil.mvvm.add.AddTaskViewModel;
import com.nikhil.mvvm.data.ListItemRepository;
import com.nikhil.mvvm.details.DetailsViewModel;
import com.nikhil.mvvm.home.MainViewModel;

/**
 * Created by nikhil on 24/12/17.
 */

public class CustomViewModelFactory implements ViewModelProvider.Factory {

    ListItemRepository repository;

    public CustomViewModelFactory(ListItemRepository repository) {
        this.repository = repository;
    }

    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(MainViewModel.class)) {
            return (T) new MainViewModel(repository);
        } else if (modelClass.isAssignableFrom(AddTaskViewModel.class)) {
            return (T) new AddTaskViewModel(repository);
        } else if (modelClass.isAssignableFrom(DetailsViewModel.class)) {
            return (T) new DetailsViewModel(repository);
        } else {
            throw new IllegalArgumentException("ViewModel Not Found");
        }
    }
}
