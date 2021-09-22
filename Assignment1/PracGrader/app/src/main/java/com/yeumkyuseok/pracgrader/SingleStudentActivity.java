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
import android.widget.Toast;

import java.util.List;

public class SingleStudentActivity extends AppCompatActivity {
    private static final String TAG = "SingleStudentActivity";

    TextView textSingleName, textSingleEmail, textSingleCountry;
    RecyclerView practicalRecyclerView;
    Button btnAddResult, btnDelete;

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
        btnDelete = (Button) findViewById(R.id.buttonDeleteStudent);

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

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data.deleteUser(student);
                Toast.makeText(SingleStudentActivity.this, "Deleted User", Toast.LENGTH_SHORT).show();
                Intent intent1 = new Intent(SingleStudentActivity.this, StaffActivity.class);
                startActivity(intent1);
            }
        });


    }

    @Override
    public void onBackPressed() {

    }
}