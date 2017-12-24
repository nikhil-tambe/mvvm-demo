package com.nikhil.mvvm.app;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by nikhil on 24/12/17.
 */

public class BaseActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public static void addFragmentToActivity(FragmentManager fragmentManager,
                                             Fragment fragment,
                                             int fragmentID,
                                             String fragmentTag){
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(fragmentID, fragment, fragmentTag);
        transaction.commit();
    }
}
