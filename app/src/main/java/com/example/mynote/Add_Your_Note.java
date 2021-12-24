package com.example.mynote;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.TextView;

import java.util.HashSet;

public class Add_Your_Note extends AppCompatActivity {
    int noteId ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__your__note);
        TextView tview =(TextView)findViewById(R.id.tview);
        Intent intent = getIntent();
        noteId = intent.getIntExtra("noteId",-1);
        if(noteId!=-1)
        {
            tview.setText(MainActivity.notes.get(noteId));
        }
        else
        {
            MainActivity.notes.add("");
            noteId = MainActivity.notes.size()-1;
        }
        tview.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                MainActivity.notes.set(noteId,String.valueOf(s));
                MainActivity.arrayAdapter.notifyDataSetChanged();
                SharedPreferences sharedPreferences =getApplicationContext().getSharedPreferences("com.example.mynote",Context.MODE_PRIVATE);
                HashSet<String> set = new HashSet<>(MainActivity.notes);
                sharedPreferences.edit().putStringSet("notes",set).apply();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
}