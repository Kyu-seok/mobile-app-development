package com.yeumkyuseok.pracgrader;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
    TextView textSearch, textLoginAs, textStatus;
    RecyclerView studentRecyclerView;

    static String staff_username;
    Data data;
    int role = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: ONCREATE!!!!");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff);

        Intent intent = getIntent();
        if (intent.hasExtra("username")) {
            staff_username = intent.getExtras().get("username").toString();
        }

        toolbar = (Toolbar) findViewById(R.id.staffActivityToolbar);
        btnStuList = (Button) findViewById(R.id.btnStudentList);
        btnInstrList = (Button) findViewById(R.id.btnInstructorList);
        btnPracList = (Button) findViewById(R.id.btnPracticalList);
        btnAddStudent = (Button) findViewById(R.id.btnAddStudent);
        btnSearch = (Button) findViewById(R.id.btnSearch);
        textSearch = (TextView) findViewById(R.id.textSearch);
        textLoginAs = (TextView) findViewById(R.id.textLoginAs);
        textStatus = (TextView) findViewById(R.id.textStatus);
        studentRecyclerView = (RecyclerView) findViewById(R.id.studentRecyclerView);

        textLoginAs.setText("Logged in as : " + staff_username);
        textStatus.setText("Status : admin");

        data = new Data();
        data.load(this);
        //data.generateData(this);    // generate data
        data.getStudentList(role);

        StudentListAdapter studentListAdapter = new StudentListAdapter(this, data.tempUsers);
        studentRecyclerView.setAdapter(studentListAdapter);
        studentRecyclerView.setLayoutManager(new LinearLayoutManager(this));



    }

}