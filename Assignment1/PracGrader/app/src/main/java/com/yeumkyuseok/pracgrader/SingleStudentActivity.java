package com.yeumkyuseok.pracgrader;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SingleStudentActivity extends AppCompatActivity {

    TextView textSingleName, textSingleEmail, textSingleCountry;
    RecyclerView practicalRecyclerView;
    Button btnAddResult;

    String name, email, country;
    String practicalNames[];
    Double markScored[], markAssigned[];



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_student);

        Data data = new Data();
        data.load(this);

        textSingleName = (TextView) findViewById(R.id.textSingleName);
        textSingleEmail = (TextView) findViewById(R.id.textSingleEmail);
        textSingleCountry = (TextView) findViewById(R.id.textSingleCountry);
        practicalRecyclerView = (RecyclerView) findViewById(R.id.singlePracticalRecyclerView);
        btnAddResult = (Button) findViewById(R.id.btnAddPractical);

        // temporarily inserted test values
        practicalNames = new String[] {"CS Lab1 ", "Chem lab 4", "MATH Assigment 1"};
        markScored = new Double[] {50.0, 60.0, 80.0};
        markAssigned = new Double[] {100.0, 100.0, 100.0};

        PracticalListAdapter practicalListAdapter = new PracticalListAdapter(this, practicalNames, markScored, markAssigned);
        practicalRecyclerView.setAdapter(practicalListAdapter);
        practicalRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        btnAddResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SingleStudentActivity.this, CRUDResultActivity.class);
                intent.putExtra("mode", 0);
                startActivity(intent);
            }
        });


    }
}