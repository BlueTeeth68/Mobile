package com.example.pesample;

import android.database.Cursor;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pesample.adapter.UserAdapter;
import com.example.pesample.dao.UserDAO;
import com.example.pesample.database.DBHelper;
import com.example.pesample.dto.UserDTO;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    private UserDAO userDAO;
    private UserAdapter userAdapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        recyclerView = findViewById(R.id.recyclerView);
        userDAO = new UserDAO(this);

        userAdapter = new UserAdapter(HomeActivity.this, getAllAccount());
        recyclerView.setAdapter(userAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(HomeActivity.this));
    }

    @Override
    protected void onResume() {
        super.onResume();
        userAdapter = new UserAdapter(HomeActivity.this, getAllAccount());
        recyclerView.setAdapter(userAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(HomeActivity.this));
    }

    private List<UserDTO> getAllAccount() {
        userDAO.open();
        List<UserDTO> result = new ArrayList<>();
        Cursor cursor = userDAO.findAll();
        if (cursor.moveToFirst()) {
            int nId = cursor.getColumnIndex(DBHelper.COL_TB1_ID);
            int nUsername = cursor.getColumnIndex(DBHelper.COL_TB1_USERNAME);
            int nFullName = cursor.getColumnIndex(DBHelper.COL_TB1_FULL_NAME);
            int nRole = cursor.getColumnIndex(DBHelper.COL_TB1_ROLE);
            do {
                int id = cursor.getInt(nId);
                String userName = cursor.getString(nUsername);
                String fullName = cursor.getString(nFullName);
                String role = cursor.getString(nRole);
                UserDTO tmp = new UserDTO();
                tmp.setId(id);
                tmp.setRole(role);
                tmp.setUsername(userName);
                tmp.setFullName(fullName);
                result.add(tmp);

            } while (cursor.moveToNext());
        }
        if (!cursor.isClosed()) {
            cursor.close();
        }
        userDAO.close();
        return result;
    }
}