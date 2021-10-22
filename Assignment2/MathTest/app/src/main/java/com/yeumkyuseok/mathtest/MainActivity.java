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
    Button btnManRegister, btnSort;
    List<Student> studentList;
    Data data;
    boolean isAsc = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_list_layout);

        btnManRegister = findViewById(R.id.buttonManualRegister);
        btnSort = findViewById(R.id.buttonSortList);

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

        btnSort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isAsc == true) {
                    data.sortA();
                    isAsc = false;
                    StudentListAdapter studentListAdapter = new StudentListAdapter(MainActivity.this, data.tempStudents);
                    studentRecyclerView.setAdapter(studentListAdapter);
                    studentRecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                } else {
                    data.sortD();
                    isAsc = true;
                    StudentListAdapter studentListAdapter = new StudentListAdapter(MainActivity.this, data.tempStudents);
                    studentRecyclerView.setAdapter(studentListAdapter);
                    studentRecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                }
            }
        });
    }
}