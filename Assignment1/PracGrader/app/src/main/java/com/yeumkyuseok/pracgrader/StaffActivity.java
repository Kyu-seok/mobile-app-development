package com.yeumkyuseok.pracgrader;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class StaffActivity extends AppCompatActivity {
    private static final String TAG = "StaffActivity";

    Toolbar toolbar;
    Button btnStuList, btnInstrList, btnPracList, btnAddStudent, btnSearch;
    TextView textSearch;
    RecyclerView studentRecyclerView;

    String names[];
    double marks[];
    Data data;
    int role = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: ONCREATE!!!!");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff);

        toolbar = (Toolbar) findViewById(R.id.staffActivityToolbar);
        btnStuList = (Button) findViewById(R.id.btnStudentList);
        btnInstrList = (Button) findViewById(R.id.btnInstructorList);
        btnPracList = (Button) findViewById(R.id.btnPracticalList);
        btnAddStudent = (Button) findViewById(R.id.btnAddStudent);
        btnSearch = (Button) findViewById(R.id.btnSearch);
        textSearch = (TextView) findViewById(R.id.textSearch);
        studentRecyclerView = (RecyclerView) findViewById(R.id.studentRecyclerView);

        data = new Data();
        data.load(this);
        //data.generateData(this);    // generate data
        //data.load(this);
        data.getStudentList(role);

        StudentListAdapter studentListAdapter = new StudentListAdapter(this, data.tempUsers);
        studentRecyclerView.setAdapter(studentListAdapter);
        studentRecyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    protected void onResume() {
        Log.d(TAG, "onResume: RESUME AGAIN!!!");
        super.onResume();
        data = new Data();
        data.load(this);
        data.getStudentList(role);

        StudentListAdapter studentListAdapter = new StudentListAdapter(this, data.tempUsers);
        studentRecyclerView.setAdapter(studentListAdapter);
        studentRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}