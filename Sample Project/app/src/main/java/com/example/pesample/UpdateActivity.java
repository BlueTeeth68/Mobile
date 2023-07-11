package com.example.pesample;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pesample.dao.UserDAO;
import com.example.pesample.database.DBHelper;

public class UpdateActivity extends AppCompatActivity {

    private UserDAO userDAO;
    private String id;
    private TextView usernameText, fullNameText, roleText;
    private EditText usernameInput, fullNameInput, roleInput;
    private Button updateButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        usernameText = findViewById(R.id.username_text);
        fullNameText = findViewById(R.id.full_name_text);
        roleText = findViewById(R.id.role_text);
        usernameInput = findViewById(R.id.userName_input);
        fullNameInput = findViewById(R.id.full_name_input);
        roleInput = findViewById(R.id.role_input);
        updateButton = findViewById(R.id.update_btn);

        userDAO = new UserDAO(UpdateActivity.this);

        loadData();

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateUser();
            }
        });

    }

    private void loadData() {
        if (getIntent().hasExtra("id")) {
            userDAO.open();
            id = getIntent().getStringExtra("id");
            Cursor cursor = userDAO.findById(id);
            if (cursor == null) {
                Toast.makeText(this, "Can not find user", Toast.LENGTH_SHORT).show();
            } else {
                int nUsername = cursor.getColumnIndex(DBHelper.COL_TB1_USERNAME);
                int nFullName = cursor.getColumnIndex(DBHelper.COL_TB1_FULL_NAME);
                int nRole = cursor.getColumnIndex(DBHelper.COL_TB1_ROLE);

                String userName = cursor.getString(nUsername);
                String fullName = cursor.getString(nFullName);
                String role = cursor.getString(nRole);
                usernameInput.setText(userName);
                fullNameInput.setText(fullName);
                roleInput.setText(role);
                if (!cursor.isClosed()) {
                    cursor.close();
                }
            }
            userDAO.close();
        }
    }

    private void updateUser() {
        userDAO.open();
        boolean isUpdateSuccess = false;
        isUpdateSuccess = userDAO.updateUser(id,
                usernameInput.getText().toString(),
                fullNameInput.getText().toString(),
                roleInput.getText().toString());
        if(isUpdateSuccess) {
            Toast.makeText(this, "Update user " + id + " success." , Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Update user " + id + " false.", Toast.LENGTH_SHORT).show();
        }
        userDAO.close();
    }


}