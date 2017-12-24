package com.nikhil.mvvm.add;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.nikhil.mvvm.R;
import com.nikhil.mvvm.app.AppClass;
import com.nikhil.mvvm.data.ListItem;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by nikhil on 24/12/17.
 */

public class AddTaskFragment extends Fragment{

    @BindView(R.id.taskTitle_EditText)
    EditText taskTitle_EditText;
    @Inject
    ViewModelProvider.Factory factory;

    AddTaskViewModel addTaskViewModel;

    public static AddTaskFragment newInstance() {
        return new AddTaskFragment();
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

        addTaskViewModel = ViewModelProviders.of(this, factory).get(AddTaskViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_add_task, container, false);
        ButterKnife.bind(this, v);

        return v;
    }

    @OnClick(R.id.addTask_Button)
    public void addTask(){
        String taskTitle = taskTitle_EditText.getText().toString();

        if (taskTitle.trim().equals("")){
            Toast.makeText(getActivity(), "Title is blank", Toast.LENGTH_SHORT).show();
            return;
        }

        String dateTime = new SimpleDateFormat("dd/MM/yyyy kk:mm:ss", Locale.ENGLISH)
                .format(new Date());
        int colorRes = R.color.colorPrimary;
        addTaskViewModel.addTaskToDB(new ListItem(dateTime, taskTitle, colorRes));

        Toast.makeText(getActivity(), "Task Added", Toast.LENGTH_SHORT).show();

    }
}
