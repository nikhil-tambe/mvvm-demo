package com.nikhil.mvvm.data;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Created by nikhil on 24/12/17.
 */

@Entity
public class ListItem {

    @PrimaryKey
    @NonNull
    private String dateTime;
    private String message;
    private int colorResource;

    public ListItem(String dateTime, String message, int colorResource) {
        this.dateTime = dateTime;
        this.message = message;
        this.colorResource = colorResource;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getColorResource() {
        return colorResource;
    }

    public void setColorResource(int colorResource) {
        this.colorResource = colorResource;
    }
}
