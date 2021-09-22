package com.yeumkyuseok.pracgrader;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

public class StudentActivity extends AppCompatActivity {

    TextView textSingleName, textSingleEmail, textSingleCountry;
    RecyclerView practicalRecyclerView;
    Data data;
    User student;

    String name, email, country, username;
    double markAvailable;
    List<TakenPrac> takenPracs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);

        textSingleName = (TextView) findViewById(R.id.textStudentViewName);
        textSingleEmail = (TextView) findViewById(R.id.textStudentViewEmail);
        textSingleCountry = (TextView) findViewById(R.id.textStudentViewCountry);
        practicalRecyclerView = (RecyclerView) findViewById(R.id.studentViewRecyclerView);

        data = new Data();
        data.load(this);

        Intent intent = getIntent();
        username = intent.getExtras().get("username").toString();
        student = data.getStudent(username);
        this.name = student.getName();
        this.email = student.getEmail();
        this.country = student.getCountry();
        takenPracs = data.getTakenPractical(username);

        textSingleName.setText(this.name);
        textSingleEmail.setText("Email : " + this.email);
        textSingleCountry.setText("Country : " + this.country);

        PracticalListAdapter practicalListAdapter = new PracticalListAdapter(this, takenPracs, username);
        practicalRecyclerView.setAdapter(practicalListAdapter);
        practicalRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }


}