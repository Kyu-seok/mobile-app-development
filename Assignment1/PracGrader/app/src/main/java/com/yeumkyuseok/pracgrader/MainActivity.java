package com.yeumkyuseok.pracgrader;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    DBHelper db = new DBHelper(this);
    UserList userList = new UserList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // DBHelper db = new DBHelper(this);
        // UserList userList = new UserList();
        userList.load(this);

        if (userList.hasAdmin()) {
            // load login
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
        } else {
            // load register
            Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
            startActivity(intent);
        }


    }
}