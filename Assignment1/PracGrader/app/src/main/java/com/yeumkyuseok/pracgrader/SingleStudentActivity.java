package com.yeumkyuseok.pracgrader;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

public class SingleStudentActivity extends AppCompatActivity {

    TextView textSingleName, textSingleEmail, textSingleCountry;
    RecyclerView practicalRecyclerView;

    String name, email, country;
    String practicalNames[];
    Double markScored[], markAssigned[];



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_student);

        UserList userList = new UserList();
        userList.load(this);

        textSingleName = (TextView) findViewById(R.id.textSingleName);
        textSingleEmail = (TextView) findViewById(R.id.textSingleEmail);
        textSingleCountry = (TextView) findViewById(R.id.textSingleCountry);
        practicalRecyclerView = (RecyclerView) findViewById(R.id.singlePracticalRecyclerView);

        // temporarily inserted test values
        practicalNames = new String[] {"CS Lab1 ", "Chem lab 4", "MATH Assigment 1"};
        markScored = new Double[] {50.0, 60.0, 80.0};
        markAssigned = new Double[] {100.0, 100.0, 100.0};

        PracticalListAdapter practicalListAdapter = new PracticalListAdapter(this, practicalNames, markScored, markAssigned);
        practicalRecyclerView.setAdapter(practicalListAdapter);
        practicalRecyclerView.setLayoutManager(new LinearLayoutManager(this));


    }
}