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

        Data data = new Data();
        data.load(this);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputUsername = textUsername.getText().toString();
                String inputPassword = textPassword.getText().toString();

                if (data.canLogin(inputUsername, inputPassword)) {
                    Toast.makeText(LoginActivity.this, "Successfully Login", Toast.LENGTH_SHORT).show();
                    int role = data.checkRole(inputUsername);   // if role is -1, there will be error
                    if (role == 0 || role == 1) {
                        if (role == 0) {
                            Toast.makeText(LoginActivity.this, "Logged in as Admin", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(LoginActivity.this, "Logged in as Instructor", Toast.LENGTH_SHORT).show();
                        }
                        Intent intent = new Intent(LoginActivity.this, StaffActivity.class);
                        intent.putExtra("role", role);
                        intent.putExtra("username", inputUsername);
                        startActivity(intent);

                    } else if (role == 2) {
                        Toast.makeText(LoginActivity.this, "Logged in as Instructor", Toast.LENGTH_SHORT).show();
                        // Todo: implement student activity
                    } else {
                        throw new IllegalArgumentException("wrong value of role");
                    }
                } else {
                    Toast.makeText(LoginActivity.this, "Try again", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onBackPressed() {

    }
}