package com.nikhil.mvvm.details;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;

import com.nikhil.mvvm.R;
import com.nikhil.mvvm.app.BaseActivity;
import com.nikhil.mvvm.home.MainFragment;

import static com.nikhil.mvvm.app.Constants.FragmentConstants.HOME_FRAG_TAG;

/**
 * Created by nikhil on 24/12/17.
 */

public class DetailsActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fragmentManager = getSupportFragmentManager();

        MainFragment mainFragment = (MainFragment) fragmentManager.findFragmentByTag(HOME_FRAG_TAG);

        if (mainFragment == null){
            mainFragment = MainFragment.newInstance();
        }

        addFragmentToActivity(fragmentManager, mainFragment, R.id.addTask_Fragment, HOME_FRAG_TAG);

    }
}
