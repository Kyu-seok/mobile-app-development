package com.yeumkyuseok.pracgrader;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CRUDInstrActivity extends AppCompatActivity {

    Button btnAdd;
    TextView textName, textEmail, textUsername, textPassword, textCountry;
    String name, email, username, password, country;
    Data data;
    int added_by;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crudinstr);

        data = new Data();
        data.load(this);

        textName = findViewById(R.id.editTextInstrName);
        textEmail = findViewById(R.id.editTextInstrEmail);
        textUsername = findViewById(R.id.editTextInstrUsername);
        textPassword = findViewById(R.id.editTextInstrPassword);
        textCountry = findViewById(R.id.editTextInstrCountry);
        btnAdd = findViewById(R.id.buttonAddInstructor);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadData();
                if (allHasData()) {
                    if (uniqueUsername(username) && correctPwFormat(password) && validateEmailAddress(email)) {
                        data.add(new User(name, email, username, password, country, 1, 0));
                        Toast.makeText(CRUDInstrActivity.this, "Successfully Added", Toast.LENGTH_SHORT).show();
                        Intent intent1 = new Intent(CRUDInstrActivity.this, StaffActivity.class);

                        startActivity(intent1);
                    }
                }
            }
        });
    }


    private void loadData() {
        if (!allHasData()) {
            Toast.makeText(this, "fill the blank", Toast.LENGTH_SHORT).show();
        }
        this.name = loadName();
        this.email = loadEmail();
        this.username = loadUsername();
        this.password = loadPassword();
        this.country = loadCountry();
    }

    public boolean validateEmailAddress(String emailAddress) {
        Pattern regexPattern;
        Matcher regMatcher;

        regexPattern = Pattern.compile("^[(a-zA-Z-0-9-\\_\\+\\.)]+@[(a-z-A-z)]+\\.[(a-zA-z)]{2,3}$");
        regMatcher   = regexPattern.matcher(emailAddress);
        if(regMatcher.matches()) {
            return true;
        } else {
            Toast.makeText(this, "Wrong email format", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    private boolean correctPwFormat(String password) {
        boolean isCorrect = false;

        try {
            if ((password.length() == 4)) {
                isCorrect = true;
            } else {
                Toast.makeText(this, "Password is 4 digit", Toast.LENGTH_SHORT).show();
            }
            return isCorrect;
        } catch (NullPointerException e) {
            Toast.makeText(this,"check the values", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    private boolean uniqueUsername(String username) {
        boolean isUnique = false;
        if (data.checkUniqueUsername(this, username)) {
            isUnique = true;
        } else {
            Toast.makeText(this, "Username already exists", Toast.LENGTH_SHORT).show();
        }
        return isUnique;
    }

    private boolean allHasData() {
        boolean allHasData = false;
        String mName, mEmail, mUsername, mPassword, mCountry;
        mName = loadName();
        mEmail = loadEmail();
        mUsername = loadUsername();
        mPassword = loadPassword();
        mCountry = loadCountry();

        if ((!mName.isEmpty()) && (!mEmail.isEmpty()) && (!mUsername.isEmpty()) && (!mPassword.isEmpty()) && (!mCountry.isEmpty())) {
            allHasData = true;
        }
        return allHasData;
    }

    public String loadName() {
        return textName.getText().toString();
    }

    public String loadEmail() {
        return textEmail.getText().toString();
    }

    public String loadUsername() {
        return textUsername.getText().toString();
    }

    public String loadPassword() {
        return textPassword.getText().toString();
    }

    public String loadCountry() {
        return textCountry.getText().toString();
    }
}