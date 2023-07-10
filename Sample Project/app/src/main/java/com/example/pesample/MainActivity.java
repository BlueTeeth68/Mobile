package com.example.pesample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.pesample.dao.UserDAO;
import com.example.pesample.dto.UserDTO;

public class MainActivity extends AppCompatActivity {

    private Button createButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createButton = findViewById(R.id.create_btn);
        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserDAO userDAO = new UserDAO(MainActivity.this);

                //create data
                UserDTO user1 = new UserDTO();
                user1.setFullName("Dao Minh Tri");
                user1.setUsername("daominhtri");
                user1.setRole("Admin");

                UserDTO user2 = new UserDTO();
                user2.setFullName("Minh Tri");
                user2.setUsername("minhtri");
                user2.setRole("User");

                UserDTO user3 = new UserDTO();
                user3.setFullName("Tri");
                user3.setUsername("tri");
                user3.setRole("User");
                //create data

                userDAO.open();
                userDAO.clearData();
                userDAO.addUser(user1);
                userDAO.addUser(user2);
                userDAO.addUser(user3);
                userDAO.close();

                Intent i = new Intent(MainActivity.this, HomeActivity.class);
                startActivity(i);
            }
        });
    }
}