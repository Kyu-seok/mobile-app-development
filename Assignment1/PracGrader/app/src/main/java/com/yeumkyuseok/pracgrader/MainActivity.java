package com.yeumkyuseok.pracgrader;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    // public UserList userList = new UserList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DBHelper db = new DBHelper(this);
        Data data = new Data();
        data.load(this);

        if (data.hasAdmin()) {
            // load login
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
        } else {
            // load register
            Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
            startActivity(intent);
        }


    }


    private void openLogin() {
        setContentView(R.layout.activity_login);
    }

    /*
    private void openRegister() {
        setContentView(R.layout.activity_register);
        boolean isUnique = false;

        Button btnCheck = (Button) findViewById(R.id.buttonCheckUnique);
        Button btnRegister = (Button) findViewById(R.id.buttonRegister);
        TextView textAdminUserName = (TextView) findViewById(R.id.textAdminUserName);
        TextView textMessage = (TextView) findViewById(R.id.textUniqueMessage);

        btnCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String adminID = textAdminUserName.getText().toString();
                if (adminID.equals(null)) {
                    Toast.makeText(MainActivity.this, "no input ID", Toast.LENGTH_SHORT).show();
                } else {
                    if (userList.checkUnique(adminID)) {

                    } else {
                        Toast.makeText(MainActivity.this, "invalid username", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


    }
     */

}