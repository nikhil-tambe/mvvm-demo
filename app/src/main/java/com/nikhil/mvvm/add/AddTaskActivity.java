package com.nikhil.mvvm.add;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;

import com.nikhil.mvvm.R;
import com.nikhil.mvvm.app.BaseActivity;

import static com.nikhil.mvvm.app.Constants.FragmentConstants.ADD_TASK_FRAG_TAG;
import static com.nikhil.mvvm.app.Constants.FragmentConstants.HOME_FRAG_TAG;

/**
 * Created by nikhil on 24/12/17.
 */

public class AddTaskActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addtask);

        FragmentManager fragmentManager = getSupportFragmentManager();

        AddTaskFragment addTaskFragment = (AddTaskFragment) fragmentManager
                .findFragmentByTag(ADD_TASK_FRAG_TAG);

        if (addTaskFragment == null){
            addTaskFragment = AddTaskFragment.newInstance();
        }

        addFragmentToActivity(fragmentManager, addTaskFragment, R.id.addTask_Fragment, ADD_TASK_FRAG_TAG);

    }
}
