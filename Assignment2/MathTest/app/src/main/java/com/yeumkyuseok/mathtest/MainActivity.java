package com.yeumkyuseok.mathtest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    RecyclerView studentRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        DBHelper db = new DBHelper(this);
        Data data = new Data();
        data.load(this);


        setContentView(R.layout.student_list_layout);
        studentRecyclerView = (RecyclerView) findViewById(R.id.studentRecyclerView);

        // temp values inputted
        data.students.add(new Student("john", "cena", "0102345678", "johnCena@gmail.com", ""));
        data.students.add(new Student("johna", "cena", "0102345678", "johnCena@gmail.com", ""));
        data.students.add(new Student("johnaa", "cena", "0102345678", "johnCena@gmail.com", ""));

        StudentListAdapter studentListAdapter = new StudentListAdapter(this, data.students);
        studentRecyclerView.setAdapter(studentListAdapter);
        studentRecyclerView.setLayoutManager(new LinearLayoutManager(this));

    }
}