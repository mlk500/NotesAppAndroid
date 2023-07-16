package com.example.notesapp.entities;

import com.example.notesapp.entities.Note;

import java.util.Comparator;

public class PrioritySortingComparator implements Comparator<Note> {

    @Override
    public int compare(Note note1, Note note2) {
        int priorityCompare = Integer.compare(note2.getPriority(), note1.getPriority());

        if (priorityCompare == 0) {
            return note1.getTitle().compareTo(note2.getTitle());
        }

        return priorityCompare;
    }
}
