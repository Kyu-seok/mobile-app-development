package com.yeumkyuseok.mathtest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    Student student;
    EmailAddr emailAddr;
    Phone phone;
    String firstName, lastName;
    TextView textFirstName, textLastName, textEmail1, textEmail2, textEmail3, textEmail4, textEmail5, textEmail6, textEmail7, textEmail8, textEmail9, textEmail10;
    TextView textPhoneNo1, textPhoneNo2, textPhoneNo3, textPhoneNo4, textPhoneNo5, textPhoneNo6, textPhoneNo7, textPhoneNo8, textPhoneNo9, textPhoneNo10;
    Button btnRegister;

    Data data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        btnRegister = findViewById(R.id.buttonRegisterStudent);
        textFirstName = findViewById(R.id.editTextRegisterFirstName);
        textLastName = findViewById(R.id.editTextRegisterLastName);
        textEmail1 = findViewById(R.id.editTextRegisterEmail1);
        textEmail2 = findViewById(R.id.editTextRegisterEmail2);
        textEmail3 = findViewById(R.id.editTextRegisterEmail3);
        textEmail4 = findViewById(R.id.editTextRegisterEmail4);
        textEmail5 = findViewById(R.id.editTextRegisterEmail5);
        textEmail6 = findViewById(R.id.editTextRegisterEmail6);
        textEmail7 = findViewById(R.id.editTextRegisterEmail7);
        textEmail8 = findViewById(R.id.editTextRegisterEmail8);
        textEmail9 = findViewById(R.id.editTextRegisterEmail9);
        textEmail10 = findViewById(R.id.editTextRegisterEmail10);
        textPhoneNo1 = findViewById(R.id.editTextRegisterPhone1);
        textPhoneNo2 = findViewById(R.id.editTextRegisterPhone2);
        textPhoneNo3 = findViewById(R.id.editTextRegisterPhone3);
        textPhoneNo4 = findViewById(R.id.editTextRegisterPhone4);
        textPhoneNo5 = findViewById(R.id.editTextRegisterPhone5);
        textPhoneNo6 = findViewById(R.id.editTextRegisterPhone6);
        textPhoneNo7 = findViewById(R.id.editTextRegisterPhone7);
        textPhoneNo8 = findViewById(R.id.editTextRegisterPhone8);
        textPhoneNo9 = findViewById(R.id.editTextRegisterPhone9);
        textPhoneNo10 = findViewById(R.id.editTextRegisterPhone10);

        data = new Data();
        data.load(this);



        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((textFirstName.getText().length() > 0 ) && (textLastName.getText().length() > 0 )) {
                    student = new Student((textFirstName.getText().toString()), textLastName.getText().toString());
                    data.addStudent(student);
                    // TODO: intent has to be to ChooseImageActivity
                    Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(RegisterActivity.this, "Please enter first & last name", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}