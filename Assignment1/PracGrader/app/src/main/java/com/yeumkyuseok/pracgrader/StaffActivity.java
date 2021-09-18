package com.yeumkyuseok.pracgrader;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class StaffActivity extends AppCompatActivity {

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
        data.generateData(this);    // generate data
        data.getStudentList(role);

        // names = new String[] {"Jane", "Kim", "john", "sam", "ken", "rafael", "santos", "kenny", "adam", "bobby"};
        // marks = new double[] {10.0, 12.3, 46, 90, 70, 80, 60, 50, 30, 100};


        StudentListAdapter studentListAdapter = new StudentListAdapter(this, data.tempUsers);
        studentRecyclerView.setAdapter(studentListAdapter);
        studentRecyclerView.setLayoutManager(new LinearLayoutManager(this));

    }


}