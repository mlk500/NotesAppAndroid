package com.example.notesapp.BL;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;

import com.example.notesapp.ViewResultHandler;
import com.example.notesapp.dao.BLResultHandler;
import com.example.notesapp.dao.NoteWebDAO;
import com.example.notesapp.entities.Note;
import com.example.notesapp.infrastructure.HttpErrorException;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import com.example.notesapp.R;

public class NoteBL {
    private static NoteBL instance = new NoteBL();
    public static NoteBL getInstance() {
        return instance;
    }
    private NoteBL() {}

    private NoteWebDAO dao = new NoteWebDAO();

    public void getAll(BLResultHandler<Note> handler) {
        dao.getAll(handler);
    }


    public void addNote(String title , String body , int priority,Activity context , ViewResultHandler<Note> handler){
        Note newNote = new Note(title,body,priority,true);
        try {
            dao.addNote(newNote, new BLResultHandler() {
                @Override
                public void onResultReady(ArrayList resultList) {
                    handler.doOnDataReceived(context,resultList);
                }
            });
        } catch (ExecutionException e) {
            System.out.println("::::::::::::::::::::::::::::::::::::::: GOT THIS CATCH ExecutionException ");
//            TextView view = context.findViewById(R.id.checking);
//            view.setVisibility(View.GONE);
        } catch (InterruptedException e) {
            System.out.println("::::::::::::::::::::::::::::::::::::::: GOT THIS CATCH InterruptedException ");
//            TextView view = context.findViewById(R.id.checking);
//            view.setVisibility(View.GONE);
        }
        catch (HttpErrorException e) {
            System.out.println("::::::::::::::::::::::::::::::::::::::: GOT THIS CATCH HttpErrorException ");
//            TextView view = context.findViewById(R.id.checking);
//            view.setVisibility(View.GONE);
        }
    }

    public void updateNote(Note note, Activity context, ViewResultHandler<Note> handler) {
        try {
            dao.updateNote(note, new BLResultHandler<Note>() {
                @Override
                public void onResultReady(ArrayList<Note> resultList) {
                    handler.doOnDataReceived(context, resultList);
                }
            });
        } catch (ExecutionException | InterruptedException | HttpErrorException e) {
            e.printStackTrace();
        }
    }


    public void deleteNote(int id, Activity context, ViewResultHandler<Note> handler){
        try {
            dao.deleteNote(id, new BLResultHandler() {
                @Override
                public void onResultReady(ArrayList resultList) {
                    handler.doOnDataReceived(context,resultList);
                }
            });
        } catch (ExecutionException | InterruptedException | HttpErrorException e) {
        }
    }

    public void deleteAllNotes(Activity context, ViewResultHandler<Note> handler) {
        try {
            dao.deleteAllNotes(new BLResultHandler() {
                @Override
                public void onResultReady(ArrayList resultList) {
                    handler.doOnDataReceived(context, resultList);
                }
            });
        } catch (ExecutionException | InterruptedException | HttpErrorException e) {
            System.out.println("Error deleting all notes: " + e);
        }
    }





}
