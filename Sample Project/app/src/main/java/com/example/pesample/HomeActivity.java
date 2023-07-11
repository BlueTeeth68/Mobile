package com.example.pesample;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pesample.adapter.UserAdapter;
import com.example.pesample.database.DBHelper;
import com.example.pesample.dto.UserDTO;
import com.example.pesample.provider.UserProvider;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    private UserAdapter userAdapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        recyclerView = findViewById(R.id.recyclerView);

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

        List<UserDTO> result = new ArrayList<>();
        ContentResolver contentResolver = getContentResolver();
        Uri uri = Uri.parse(UserProvider.URL_TABLE_1);


        Cursor cursor = contentResolver.query(uri, new String[]{DBHelper.COL_TB1_ID, DBHelper.COL_TB1_USERNAME, DBHelper.COL_TB1_FULL_NAME, DBHelper.COL_TB1_ROLE}, null, null, null);
        if (cursor != null) {
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
        }
        return result;
    }
}