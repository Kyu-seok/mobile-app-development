package com.yeumkyuseok.pracgrader;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    TextView textUsername;
    TextView textPassword;
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        textUsername = (TextView) findViewById(R.id.textUserName);
        textPassword = (TextView) findViewById(R.id.textPassword);
        btnLogin = (Button) findViewById(R.id.buttonLogin);

        UserList userList = new UserList();
        userList.load(this);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputUsername = textUsername.getText().toString();
                String inputPassword = textPassword.getText().toString();

                if (userList.canLogin(inputUsername, inputPassword)) {
                    Toast.makeText(LoginActivity.this, "Successfully Login", Toast.LENGTH_SHORT).show();
                    int role = userList.checkRole(inputUsername);   // if role is -1, there will be error
                    if (role == 0) {
                        Toast.makeText(LoginActivity.this, "role is admin", Toast.LENGTH_SHORT).show();
                        // todo: make new activity of admin and change intent
                        // I wonder if there will be error as there is no break after this if statement.
                    } else if (role == 1) {

                        Toast.makeText(LoginActivity.this, "role is not admin", Toast.LENGTH_SHORT).show();
                    } else if (role == 2) {

                        Toast.makeText(LoginActivity.this, "role is not admin", Toast.LENGTH_SHORT).show();
                    } else {
                        throw new IllegalArgumentException("wrong value of role");
                    }
                } else {
                    Toast.makeText(LoginActivity.this, "Try again", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}