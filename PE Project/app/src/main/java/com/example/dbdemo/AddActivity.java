package com.example.dbdemo;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class AddActivity extends AppCompatActivity {
    private EditText title_input, author_input, pages_input;
    private Button addButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        title_input = findViewById(R.id.title_input);
        author_input = findViewById(R.id.author_input);
        pages_input = findViewById(R.id.pages_input);
        addButton = findViewById(R.id.add_button);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDBHelper myDB = new MyDBHelper(AddActivity.this);
                myDB.addBook(title_input.getText().toString().trim(),
                        author_input.getText().toString().trim(),
                        //need to try catch the input value to handle error
                        Integer.valueOf(pages_input.getText().toString().trim()));
            }
        });
    }
}