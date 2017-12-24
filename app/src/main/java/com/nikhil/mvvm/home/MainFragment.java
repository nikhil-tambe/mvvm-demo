package com.nikhil.mvvm.home;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.nikhil.mvvm.R;
import com.nikhil.mvvm.add.AddTaskActivity;
import com.nikhil.mvvm.app.AppClass;
import com.nikhil.mvvm.data.ListItem;

import java.util.List;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by nikhil on 24/12/17.
 */

public class MainFragment extends Fragment {

    private static final String TAG = "MainFragment";
    private List<ListItem> listItems;

    @Inject
    ViewModelProvider.Factory viewFactory;

    MainViewModel mainViewModel;

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ((AppClass) getActivity().getApplication())
                .getAppComponent()
                .inject(this);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mainViewModel = ViewModelProviders.of(this, viewFactory).get(MainViewModel.class);

        mainViewModel.getListOfData().observe(this, new Observer<List<ListItem>>() {
            @Override
            public void onChanged(@Nullable List<ListItem> listItems) {
                if (MainFragment.this.listItems == null){
                    setListData(listItems);
                }
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_list, container, false);
        ButterKnife.bind(this, v);

        //Toast.makeText(getActivity(), "Created", Toast.LENGTH_SHORT).show();

        return v;
    }

    @OnClick(R.id.floatingActionButton)
    public void fabClicked(){
        startActivity(new Intent(getActivity(), AddTaskActivity.class));
    }

    private void setListData(List<ListItem> listItems) {
        this.listItems = listItems;

        Log.d(TAG, "setListData: ===== DB Started =====");
        for (ListItem item : listItems){
            Log.d(TAG, "setListData: " + item.getDateTime() + ": " + item.getMessage());
        }
        Log.d(TAG, "setListData: ===== Ended =====");

    }

    private void deleteTask(ListItem item){
        mainViewModel.deleteTask(item);
    }
}
