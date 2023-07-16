package com.example.notesapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.notesapp.BL.NoteBL;
import com.example.notesapp.entities.Note;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.slider.Slider;

import java.util.ArrayList;
import java.util.List;

public class NoteBody extends AppCompatActivity {
    final int[] priority = new int[1];
    EditText titleTxt;
    EditText bodyTxt;
    Slider slider;
    private String originalTitle;
    private String originalBody;
    private int originalPriority;
    private MaterialButton editBtn;
    private Note note;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_body);
        note = (Note) getIntent().getSerializableExtra("note");
        editBtn = findViewById(R.id.editBtn);
        bodyTxt = findViewById(R.id.body_txt);
        titleTxt = findViewById(R.id.title_txt);
        slider = findViewById(R.id.slider_nums);
        FloatingActionButton deleteBtn = findViewById(R.id.deleteBtn);
        disableEditBtn();
        fillInfo(note);
        titleTxt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(!(titleTxt.getText().toString().equals(originalTitle))){
                    enableEditBtn();
                }
                else{
                    disableEditBtn();
                }
            }
        });

        bodyTxt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(!(bodyTxt.getText().toString().equals(originalBody))){
                    enableEditBtn();
                }
                else{
                    disableEditBtn();
                }
            }
        });
        slider.addOnChangeListener(new Slider.OnChangeListener() {
            @Override
            public void onValueChange(@NonNull Slider slider, float value, boolean fromUser) {
                if( value != originalPriority){
                    priority[0] = ((int) value);
                    enableEditBtn();
                }
                else{
                    disableEditBtn();
                }
            }
        });

        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(titleTxt.getText().toString().isEmpty()){
                    titleTxt.setError("Title Cannot Be Empty!");
                    return;
                }
                else{
                    note.setTitle(titleTxt.getText().toString());
                    note.setBody(bodyTxt.getText().toString());
                    note.setPriority(priority[0]);
                    NoteBL.getInstance().updateNote(note, NoteBody.this, new ViewResultHandler<Note>() {
                        @Override
                        void onDataReceived(ArrayList<Note> resultList) {
                            Note updatedNote = resultList.get(0);
                        }
                    });
                    Toast.makeText(getApplicationContext(),"Note Updated",Toast.LENGTH_SHORT).show();
                    homeScreen();
                }

            }
        });
        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int noteId = note.getId();
                new AlertDialog.Builder(NoteBody.this)
                        .setTitle("Are you sure you want to delete " + note.getTitle().toString() + " note?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                NoteBL.getInstance().deleteNote(noteId, NoteBody.this, new ViewResultHandler<Note>() {  // use the id here
                                    @Override
                                    void onDataReceived(ArrayList<Note> resultList) {
                                        Note updatedNote = resultList.get(0);
                                    }
                                });
                                homeScreen();
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

    private void fillInfo(Note note) {
        titleTxt.setText(note.getTitle());
        bodyTxt.setText(note.getBody());
        slider.setValue(note.getPriority());
        priority[0] = note.getPriority();
        originalTitle = note.getTitle();
        originalBody = note.getBody();
        originalPriority = note.getPriority();
    }

    public void homeScreen(){
        Intent intent = new Intent(NoteBody.this,MainActivity.class);
        startActivity(intent);
    }

    public void enableEditBtn(){
        editBtn.setClickable(true);
        editBtn.setEnabled(true);
    }
    public void disableEditBtn(){
        editBtn.setClickable(false);
        editBtn.setEnabled(false);
    }
}

