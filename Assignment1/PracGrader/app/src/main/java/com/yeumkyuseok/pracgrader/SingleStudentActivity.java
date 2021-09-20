package com.yeumkyuseok.pracgrader;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class SingleStudentActivity extends AppCompatActivity {
    private static final String TAG = "SingleStudentActivity";

    TextView textSingleName, textSingleEmail, textSingleCountry;
    RecyclerView practicalRecyclerView;
    Button btnAddResult;

    Data data;
    User student;

    String name, email, country, username;
    double markAvailable;
    List<TakenPrac> takenPracs;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_student);

        textSingleName = (TextView) findViewById(R.id.textSingleName);
        textSingleEmail = (TextView) findViewById(R.id.textSingleEmail);
        textSingleCountry = (TextView) findViewById(R.id.textSingleCountry);
        practicalRecyclerView = (RecyclerView) findViewById(R.id.singlePracticalRecyclerView);
        btnAddResult = (Button) findViewById(R.id.btnAddPractical);

        data = new Data();
        data.load(this);

        Intent intent = getIntent();
        if (intent.hasExtra("username")) {
            username = intent.getExtras().getString("username");
        }
        student = data.getStudent(username);

        if (student == null) {
            Log.d(TAG, "onCreate: null student value");
        } else {
            this.name = student.getName();
            this.email = student.getEmail();
            this.country = student.getCountry();
        }

        takenPracs = data.getTakenPractical(username);

        // temporarily inserted test values
        textSingleName.setText(this.name);
        textSingleEmail.setText("Email : " + this.email);
        textSingleCountry.setText("Country : " + this.country);

        PracticalListAdapter practicalListAdapter = new PracticalListAdapter(this, takenPracs, username);
        practicalRecyclerView.setAdapter(practicalListAdapter);
        practicalRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        btnAddResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SingleStudentActivity.this, CRUDResultActivity.class);
                intent.putExtra("mode", 0);
                intent.putExtra("username", username);
                startActivity(intent);
            }
        });


    }

    @Override
    public void onBackPressed() {

    }
}