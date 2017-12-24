package com.nikhil.mvvm.details;

import android.os.Bundle;
import android.support.v4.app.Fragment;

/**
 * Created by nikhil on 24/12/17.
 */

public class DetailsFragment extends Fragment {


    public static DetailsFragment newInstance() {

        Bundle args = new Bundle();

        DetailsFragment fragment = new DetailsFragment();
        fragment.setArguments(args);
        return fragment;
    }



}
