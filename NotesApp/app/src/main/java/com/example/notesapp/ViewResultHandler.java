package com.example.notesapp;

import android.app.Activity;

import java.util.ArrayList;

public abstract class ViewResultHandler<T> {
    public void doOnDataReceived(Activity context , ArrayList<T> list){
        context.runOnUiThread(new Runnable() {
            public void run() {
                    onDataReceived(list);
                }
        });
    }
    abstract void onDataReceived(ArrayList<T> resultList);
}
