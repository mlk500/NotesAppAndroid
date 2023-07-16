package com.example.notesapp.dao;

import com.example.notesapp.entities.Note;

import java.util.List;

public interface NoteDAO {
    public List<Note> getAll(BLResultHandler<Note> callback);
}
