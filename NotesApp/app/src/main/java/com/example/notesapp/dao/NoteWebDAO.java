package com.example.notesapp.dao;

import com.example.notesapp.entities.Note;
import com.example.notesapp.infrastructure.HttpErrorException;
import com.example.notesapp.infrastructure.InternetConnectionManager;
import com.example.notesapp.infrastructure.OnDataReceived;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class NoteWebDAO implements NoteDAO{
    private InternetConnectionManager connectionManager = InternetConnectionManager.getInstance();
    private List<Note> notes = new ArrayList<>();

    @Override
    public List<Note> getAll(BLResultHandler<Note> callback) {
        try {
            connectionManager.callGetEndpoint("notes/getAll", new OnDataReceived() {
                @Override
                public void dataReceived(String data) {
                    try {
                        JSONArray jsonNotesArr = new JSONArray(data);
                        ArrayList<Note> notes = new ArrayList<>();
                        for(int i=0; i<jsonNotesArr.length();i++)
                        {
                            JSONObject noteJSON = jsonNotesArr.getJSONObject(i);
                            Note note = new Note(noteJSON);
                            notes.add(note);
                        }
                        callback.onResultReady(notes);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });

        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return notes;
    }

    public void addNote(Note note ,BLResultHandler handler) throws ExecutionException, HttpErrorException, InterruptedException {
        try {
            JSONObject noteJson = note.toJson();
            String str = noteJson.toString();
            connectionManager.callPostEndpoint("notes/add",str,new OnDataReceived(){
                @Override
                public void dataReceived(String data) throws JSONException {
                    ArrayList<Note> result = new ArrayList<>();
                    JSONObject noteJson = new JSONObject(data);
                    result.add(new Note(noteJson));
                    handler.onResultReady(result);
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void updateNote(Note note, BLResultHandler handler) throws ExecutionException, HttpErrorException, InterruptedException {
        try {
            JSONObject noteJson = note.toJson();
            String str = noteJson.toString();
            connectionManager.callPutEndpoint("notes/updateNote/" + note.getId(), str, new OnDataReceived() {
                @Override
                public void dataReceived(String data) throws JSONException {
                    ArrayList<Note> result = new ArrayList<>();
                    JSONObject noteJson = new JSONObject(data);
                    result.add(new Note(noteJson));
                    handler.onResultReady(result);
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


//    public void deleteNote(int id ,BLResultHandler handler ) throws ExecutionException, InterruptedException {
//        connectionManager.callDeleteEndpoint("notes/delete/"+id,new OnDataReceived(){
//            @Override
//            public void dataReceived(String data) throws JSONException {
//                ArrayList<String> result = new ArrayList<>();
//                result.add(data);
//                handler.onResultReady(result);
//            }
//        });
//    }
    public void deleteNote(int id, BLResultHandler handler) throws ExecutionException, InterruptedException, HttpErrorException {
        connectionManager.callDeleteEndpoint("notes/delete/" + id, new OnDataReceived() {
            @Override
            public void dataReceived(String data) throws JSONException {
                ArrayList<Note> result = new ArrayList<>();
                JSONObject noteJson = new JSONObject(data);
                result.add(new Note(noteJson));
                handler.onResultReady(result);
            }
        });
    }

    public void deleteAllNotes(BLResultHandler handler) throws ExecutionException, HttpErrorException, InterruptedException {
        connectionManager.callDeleteEndpoint("notes/deleteAll", new OnDataReceived(){
            @Override
            public void dataReceived(String data) throws JSONException {
                ArrayList<Note> result = new ArrayList<>();
                handler.onResultReady(result);
            }
        });
    }



}

