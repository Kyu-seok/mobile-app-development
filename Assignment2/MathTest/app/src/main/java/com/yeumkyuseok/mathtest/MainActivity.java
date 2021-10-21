package com.yeumkyuseok.mathtest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView studentRecyclerView;
    Button btnManRegister, btnLoadContact;
    List<Student> studentList;
    Data data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_list_layout);

        btnManRegister = findViewById(R.id.buttonManualRegister);
        btnLoadContact = findViewById(R.id.buttonLoadContact);

        DBHelper db = new DBHelper(this);
        data = new Data();
        data.load(this);

        studentRecyclerView = (RecyclerView) findViewById(R.id.studentRecyclerView);


        StudentListAdapter studentListAdapter = new StudentListAdapter(this, data.students);
        studentRecyclerView.setAdapter(studentListAdapter);
        studentRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        btnManRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

    }
}