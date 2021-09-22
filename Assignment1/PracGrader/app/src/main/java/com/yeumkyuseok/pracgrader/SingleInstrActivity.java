package com.yeumkyuseok.pracgrader;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SingleInstrActivity extends AppCompatActivity {

    String name, email, username, country, password;
    Button btnConfirm, btnDelete;
    TextView textName, textEmail, textCountry;
    Data data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_instr);

        btnConfirm = findViewById(R.id.buttonSingleInstrConfirm);
        btnDelete= findViewById(R.id.buttonSingleInstrDelete);
        textName = findViewById(R.id.textInstrSingleName);
        textEmail = findViewById(R.id.textInstrSingleEmail);
        textCountry = findViewById(R.id.textInstrSingleCountry);

        data = new Data();
        data.load(this);

        Intent intent = getIntent();
        username = intent.getExtras().get("username").toString();

        User instructor = data.getStudent(username);
        password = instructor.getPassword();

        textName.setText(instructor.getName());
        textEmail.setText(instructor.getEmail());
        textCountry.setText(instructor.getCountry());

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadData();
                if (validateEmailAddress(email)) {
                    User newUser = new User(name, email, username, password, country, 1, 0);
                    data.editUser(SingleInstrActivity.this, username, newUser);
                    Toast.makeText(SingleInstrActivity.this, "Successfully Edited", Toast.LENGTH_SHORT).show();
                    Intent intent1 = new Intent(SingleInstrActivity.this, StaffActivity.class);
                    startActivity(intent1);
                }
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data.deleteUser(instructor);
                Toast.makeText(SingleInstrActivity.this, "Successfully Deleted", Toast.LENGTH_SHORT).show();
                Intent intent1 = new Intent(SingleInstrActivity.this, StaffActivity.class);
                startActivity(intent1);
            }
        });

    }

    private void loadData() {
        if (!allHasData()) {
            Toast.makeText(this, "fill the blank", Toast.LENGTH_SHORT).show();
        }
        this.name = loadName();
        this.email = loadEmail();
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

    private boolean allHasData() {
        boolean allHasData = false;
        String mName, mEmail, mCountry;
        mName = loadName();
        mEmail = loadEmail();
        mCountry = loadCountry();

        if ((!mName.isEmpty()) && (!mEmail.isEmpty()) && (!mCountry.isEmpty())) {
            allHasData = true;
        }
        return allHasData;
    }

    private String loadName() {
        try {
            return textName.getText().toString();
        } catch (Exception e) {
            return null;
        }
    }

    private String loadEmail() {
        try {
            return textEmail.getText().toString();
        } catch (Exception e) {
            return null;
        }
    }

    private String loadCountry() {
        try {
            return textCountry.getText().toString();
        } catch (Exception e) {
            return null;
        }
    }
}