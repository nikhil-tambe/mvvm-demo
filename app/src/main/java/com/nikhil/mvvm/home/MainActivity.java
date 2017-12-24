package com.nikhil.mvvm.home;

import android.support.v4.app.FragmentManager;
import android.os.Bundle;

import com.nikhil.mvvm.R;
import com.nikhil.mvvm.app.BaseActivity;

import static com.nikhil.mvvm.app.Constants.FragmentConstants.HOME_FRAG_TAG;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fragmentManager = getSupportFragmentManager();

        MainFragment mainFragment = (MainFragment) fragmentManager.findFragmentByTag(HOME_FRAG_TAG);

        if (mainFragment == null){
            mainFragment = MainFragment.newInstance();
        }

        addFragmentToActivity(fragmentManager, mainFragment, R.id.mainActivity_Fragment, HOME_FRAG_TAG);

    }
}
