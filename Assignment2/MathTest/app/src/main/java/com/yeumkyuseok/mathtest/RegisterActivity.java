package com.yeumkyuseok.mathtest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
                    student = new Student((textFirstName.getText().toString()), textLastName.getText().toString(), 0);
                    // data.addStudent(student)f;
                    emailAddr = new EmailAddr(student.getFullName());
                    phone = new Phone(student.getFullName());
                    if (textEmail1.getText().length() > 0) {
                        emailAddr.addEmail(textEmail1.getText().toString());
                    }
                    if (textEmail2.getText().length() > 0) {
                        emailAddr.addEmail(textEmail2.getText().toString());
                    }
                    if (textEmail3.getText().length() > 0) {
                        emailAddr.addEmail(textEmail3.getText().toString());
                    }
                    if (textEmail4.getText().length() > 0) {
                        emailAddr.addEmail(textEmail4.getText().toString());
                    }
                    if (textEmail5.getText().length() > 0) {
                        emailAddr.addEmail(textEmail5.getText().toString());
                    }
                    if (textEmail6.getText().length() > 0) {
                        emailAddr.addEmail(textEmail6.getText().toString());
                    }
                    if (textEmail7.getText().length() > 0) {
                        emailAddr.addEmail(textEmail7.getText().toString());
                    }
                    if (textEmail8.getText().length() > 0) {
                        emailAddr.addEmail(textEmail8.getText().toString());
                    }
                    if (textEmail9.getText().length() > 0) {
                        emailAddr.addEmail(textEmail9.getText().toString());
                    }
                    if (textEmail10.getText().length() > 0) {
                        emailAddr.addEmail(textEmail10.getText().toString());
                    }

                    if (textPhoneNo1.getText().length() > 0) {
                        phone.addPhoneNo(textPhoneNo1.getText().toString());
                    }
                    if (textPhoneNo2.getText().length() > 0) {
                        phone.addPhoneNo(textPhoneNo2.getText().toString());
                    }
                    if (textPhoneNo3.getText().length() > 0) {
                        phone.addPhoneNo(textPhoneNo3.getText().toString());
                    }
                    if (textPhoneNo4.getText().length() > 0) {
                        phone.addPhoneNo(textPhoneNo4.getText().toString());
                    }
                    if (textPhoneNo5.getText().length() > 0) {
                        phone.addPhoneNo(textPhoneNo5.getText().toString());
                    }
                    if (textPhoneNo6.getText().length() > 0) {
                        phone.addPhoneNo(textPhoneNo6.getText().toString());
                    }
                    if (textPhoneNo7.getText().length() > 0) {
                        phone.addPhoneNo(textPhoneNo7.getText().toString());
                    }
                    if (textPhoneNo8.getText().length() > 0) {
                        phone.addPhoneNo(textPhoneNo8.getText().toString());
                    }
                    if (textPhoneNo9.getText().length() > 0) {
                        phone.addPhoneNo(textPhoneNo9.getText().toString());
                    }
                    if (textPhoneNo10.getText().length() > 0) {
                        phone.addPhoneNo(textPhoneNo10.getText().toString());
                    }


                    if (allEmailsAreValid(emailAddr.getEmails()) && allPhonesAreValid(phone.getPhoneNumbers())) {
                        data.addEmail(emailAddr);
                        data.addPhone(phone);

                        student.setEmails(emailAddr.getEmails());
                        student.setPhones(phone.getPhoneNumbers());

                        Intent intent = new Intent(RegisterActivity.this, ChooseImageActivity.class);
                        intent.putExtra("student",student);
                        startActivity(intent);
                    } else {
                        emailAddr.emails.clear();
                        phone.getPhoneNumbers().clear();
                        Toast.makeText(RegisterActivity.this, "Please enter valid email and phoneNumber", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(RegisterActivity.this, "Please enter first & last name", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    public boolean allPhonesAreValid(List<String> phones) {
        boolean valid = true;
        for (int i = 0; i < phones.size(); i++) {
            if (!isNumeric(phones.get(i))) {
                valid = false;
            }
        }
        return valid;
    }

    private Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");

    public boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        return pattern.matcher(strNum).matches();
    }


    public boolean allEmailsAreValid(List<String> emails) {
        boolean valid = true;
        for (int i = 0; i < emails.size(); i++) {
            if (!validateEmailAddress(emails.get(i))) {
                valid = false;
            }
        }
        return valid;
    }

    public boolean validateEmailAddress(String emailAddress) {
        Pattern regexPattern;
        Matcher regMatcher;

        regexPattern = Pattern.compile("^[(a-zA-Z-0-9-\\_\\+\\.)]+@[(a-z-A-z)]+\\.[(a-zA-z)]{2,3}$");
        regMatcher   = regexPattern.matcher(emailAddress);
        if(regMatcher.matches()) {
            return true;
        } else {
            // Toast.makeText(this, "Wrong email format", Toast.LENGTH_SHORT).show();
            return false;
        }
    }
}