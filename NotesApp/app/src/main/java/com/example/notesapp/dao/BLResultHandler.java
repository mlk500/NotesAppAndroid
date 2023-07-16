package com.example.notesapp.dao;

import java.util.ArrayList;

public interface BLResultHandler<T> {
    public void onResultReady(ArrayList<T> resultList);
}
