package com.example.notesapp.infrastructure;

import org.json.JSONException;

public interface OnDataReceived {
    public void dataReceived(String data) throws JSONException;
}
