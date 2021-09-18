package com.yeumkyuseok.pracgrader;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    Boolean isChecked = false;
    String inputAdminUserName= "";

    Button btnCheck;
    Button btnRegister;
    TextView textAdminUserName;
    TextView textMessage;
    TextView password1;
    TextView password2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        btnCheck = (Button) findViewById(R.id.buttonCheckUnique);
        btnRegister = (Button) findViewById(R.id.buttonRegister);
        textAdminUserName = (TextView) findViewById(R.id.textAdminUserName);
        textMessage = (TextView) findViewById(R.id.textUniqueMessage);
        password1 = (TextView) findViewById(R.id.textPassword1);
        password2 = (TextView) findViewById(R.id.textPassword2);

        UserList userList = new UserList();
        userList.load(this);



        btnCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String adminID = textAdminUserName.getText().toString();
                inputAdminUserName = textAdminUserName.getText().toString();
                if (adminID.isEmpty()) {
                    Toast.makeText(RegisterActivity.this, "no input ID", Toast.LENGTH_SHORT).show();
                } else {
                    if (userList.checkUnique(adminID)) {
                        RegisterActivity.this.isChecked = true;
                        textMessage.setText("username is unique");
                    } else {
                        Toast.makeText(RegisterActivity.this, "invalid username", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (confirmUnique() && passwordCheck()) {
                    User admin = new User("admin", "", inputAdminUserName, password1.getText().toString(), "", 0, -1);
                    userList.add(admin);
                    Toast.makeText(RegisterActivity.this, "Successfully created Admin account", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
            }
        });


    }

    private boolean passwordCheck() {
        boolean correctFormat = false;
        String pw1 = password1.getText().toString();
        String pw2 = password2.getText().toString();

        if (pw1.equals(pw2)) {
            if ((pw1.length() == 4) && pw2.length() == 4) {
                correctFormat = true;
            } else {
                Toast.makeText(RegisterActivity.this, "Password has to be 4 digit", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(RegisterActivity.this, "Passwords does not match", Toast.LENGTH_SHORT).show();
        }

        return correctFormat;
    }

    private boolean confirmUnique() {
        boolean isConfirmed = false;
        if (isChecked && inputAdminUserName.equals(textAdminUserName.getText().toString())) {
            isConfirmed = true;
        } else {
            textMessage.setText("check username");
            Toast.makeText(RegisterActivity.this, "Please check username", Toast.LENGTH_SHORT).show();
        }
        return isConfirmed;
    }
}