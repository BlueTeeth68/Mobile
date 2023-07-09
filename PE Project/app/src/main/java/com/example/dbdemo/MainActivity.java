package com.example.dbdemo;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private FloatingActionButton addButton;
    private MyDBHelper db;
    private ArrayList<String> bookId, bookTitle, bookAuthor, bookPages;
    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        addButton = findViewById(R.id.addButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                startActivity(intent);
            }
        });

        db = new MyDBHelper(MainActivity.this);
        bookId = new ArrayList<>();
        bookTitle = new ArrayList<>();
        bookAuthor = new ArrayList<>();
        bookPages = new ArrayList<>();

        storeDataInArray();
        customAdapter = new CustomAdapter(MainActivity.this, bookId, bookTitle, bookAuthor, bookPages);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
    }

    private void storeDataInArray() {
        Cursor cursor = db.readAllData();
        if (cursor == null) {
            Toast.makeText(this, "Cursor is null", Toast.LENGTH_SHORT).show();
            return;
        }
        if (cursor.getCount() == 0) {
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                bookId.add(cursor.getString(0));
                bookTitle.add(cursor.getString(1));
                bookAuthor.add(cursor.getString(2));
                bookPages.add(cursor.getString(3));
            }
        }

    }
}