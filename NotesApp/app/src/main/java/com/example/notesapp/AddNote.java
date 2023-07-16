package com.example.notesapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.ActionBar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.notesapp.BL.NoteBL;
import com.example.notesapp.entities.Note;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.slider.LabelFormatter;
import com.google.android.material.slider.Slider;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class AddNote extends AppCompatActivity {

    final int[] priority = new int[1];
    EditText titleInput;
    EditText bodyInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Add New Note");
        priority[0] = 1;
        bodyInput = findViewById(R.id.body_txt);
        titleInput = findViewById(R.id.title_txt);
        MaterialButton saveBtn = findViewById(R.id.save_btn);
        Slider slider = findViewById(R.id.slider_nums);
        slider.addOnChangeListener(new Slider.OnChangeListener() {
            @Override
            public void onValueChange(@NonNull Slider slider, float value, boolean fromUser) {
                priority[0] = ((int) value);
            }
        });

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                processForm();
            }
        });

    }

    public void processForm(){
        if(titleInput.getText().toString().isEmpty()){
            titleInput.setError("Title Cannot Be Empty!");
            return;
        }
        else{
            addNoteTriggered();
        }
    }


    public void addNoteTriggered(){
        String titleStr = titleInput.getText().toString();
        String bodyStr = bodyInput.getText().toString();
        NoteBL.getInstance().addNote(titleStr, bodyStr, priority[0], AddNote.this, new ViewResultHandler<Note>() {
            @Override
            void onDataReceived(ArrayList<Note> resultList) {
                Note note = resultList.get(0);
            }
        });
        Toast.makeText(getApplicationContext(),"Note Saved",Toast.LENGTH_SHORT).show();
        homeScreen();
    }
    public void homeScreen(){
        Intent intent = new Intent(AddNote.this,MainActivity.class);
        startActivity(intent);
    }
}