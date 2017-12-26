package com.nikhil.mvvm.app;

/**
 * Created by nikhil on 24/12/17.
 */

public interface Constants {

    interface DatabaseConstants{
        String DB_NAME = "room_demo.db";
        int DB_VERSION = 1;
    }

    interface FragmentConstants {
        String HOME_FRAG_TAG = "HomeFrag";
        String ADD_TASK_FRAG_TAG = "AddTaskFrag";
        String DETAILS_FRAG_TAG = "DetailsFrag";
    }

    interface Intents {
        String EXTRA_ITEM_ID = "EXTRA_ITEM_ID";
    }

}
