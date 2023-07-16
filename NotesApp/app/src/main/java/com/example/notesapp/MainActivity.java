package com.example.notesapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.notesapp.BL.NoteBL;
import com.example.notesapp.dao.BLResultHandler;
import com.example.notesapp.entities.Note;
import com.example.notesapp.entities.PrioritySortingComparator;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    List<Note> notesList = new ArrayList<Note>();
    Note selectedNote;
    ListView listView;
    RecyclerView recyclerView;
    NoteAdapter adapter;
    MaterialButton deleteAllBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);
        deleteAllBtn = findViewById(R.id.deleteAll);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        initData();
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Notes App");
        System.out.println("%%%%%%%%%% start");
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchAdd();           }
        });
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);
        deleteAllBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Are you sure you want to delete all notes?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                NoteBL.getInstance().deleteAllNotes(MainActivity.this, new ViewResultHandler<Note>() {
                                    @Override
                                    void onDataReceived(ArrayList<Note> resultList) {
                                        notesList.clear();
                                        adapter.notifyDataSetChanged();
                                    }
                                });
                            }
                        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        }).create().show();
            }
        });
    }

    public void launchAdd(){
        Intent intent = new Intent(MainActivity.this, AddNote.class);
        startActivity(intent);
    }

    public void initData() {
        NoteBL.getInstance().getAll(new BLResultHandler<Note>() {
            @Override
            public void onResultReady(ArrayList<Note> resultList) {
                notesList = resultList;
                Collections.sort(notesList, new PrioritySortingComparator());
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter = new NoteAdapter(notesList);
                        recyclerView.setAdapter(adapter);
                        adapter.setOnItemClickListener(new NoteAdapter.OnItemClickListener() {
                            @Override
                            public void onItemClick(Note note) {
                                showNote(note);
                            }
                        });
                    }
                });
            }
        });
    }


    public void showNote(Note note){
        Intent intent = new Intent(MainActivity.this,NoteBody.class);
        intent.putExtra("note", note);
        startActivity(intent);
    }

    ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            int position = viewHolder.getAdapterPosition();
            int noteId = notesList.get(position).getId();  // capture the id here
            new AlertDialog.Builder(MainActivity.this)
                    .setTitle("Are you sure you want to delete " + notesList.get(position).getTitle().toString() + " note?")
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            NoteBL.getInstance().deleteNote(noteId, MainActivity.this, new ViewResultHandler<Note>() {  // use the id here
                                @Override
                                void onDataReceived(ArrayList<Note> resultList) {
                                    Note updatedNote = resultList.get(0);
                                }
                            });
                            // check if the position is valid before accessing the list:
                            if (position < notesList.size()) {
                                notesList.remove(position);
                                adapter.notifyItemRemoved(position);
                            }
                        }
                    }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                            if (position < notesList.size()) {
                                adapter.notifyItemChanged(position);
                            }
                        }
                    }).create().show();
        }
    };

}