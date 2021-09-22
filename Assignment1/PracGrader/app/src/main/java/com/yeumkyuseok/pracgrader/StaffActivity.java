package com.yeumkyuseok.pracgrader;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.net.StandardProtocolFamily;

public class StaffActivity extends AppCompatActivity {
    private static final String TAG = "StaffActivity";

    Toolbar toolbar;
    Button btnStuList, btnInstrList, btnPracList, btnAddStudent, btnSearch;
    TextView textSearch, textLoginAs, textStatus, textListTitle;
    RecyclerView studentRecyclerView;

    static String staff_username;
    Data data;
    public static int role = 0;
    int viewStatus = 0; // | View Student = 0 | View Instructors = 1 | View Practicals = 2 |



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: ONCREATE!!!!");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff);

        Intent intent = getIntent();
        if (intent.hasExtra("username")) {
            staff_username = intent.getExtras().get("username").toString();
        }
        if (intent.hasExtra("role")) {
            role =  Integer.parseInt(intent.getExtras().get("role").toString());
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
        textListTitle = (TextView) findViewById(R.id.textListTitle);
        studentRecyclerView = (RecyclerView) findViewById(R.id.studentRecyclerView);

        if (role == 0) {

            textLoginAs.setText("Logged in as : " + staff_username);
            textStatus.setText("Status : admin");
            btnAddStudent.setBackgroundColor(Color.parseColor("#3CB043"));

            btnStuList.setOnClickListener(viewStudentOnClickListener);
            btnSearch.setOnClickListener(searchStudentOnClickListener);
            btnInstrList.setOnClickListener(viewInstrOnClickListener);
            btnPracList.setOnClickListener(viewPracticalOnClickListener);

            btnAddStudent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    switch (viewStatus) {
                        case 0:
                            addStudent();
                            break;
                        case 1:
                            addInstructor();
                            break;
                        case 2:
                            addPractical();
                            break;
                        default:
                            addStudent();
                    }
                }
            });

            viewStudent();

        } else {
            textLoginAs.setText("Logged in as : " + staff_username);
            textStatus.setText("Status : Instructor");
            btnAddStudent.setBackgroundColor(Color.parseColor("#3CB043"));

            btnStuList.setVisibility(View.GONE);
            btnInstrList.setVisibility(View.GONE);
            btnPracList.setVisibility(View.GONE);
            btnSearch.setOnClickListener(searchStudentOnClickListener);

            btnAddStudent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    addStudent();
                }
            });

            viewStudent();
        }

    }

    private View.OnClickListener viewStudentOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            btnAddStudent.setText("add student");
            btnAddStudent.setBackgroundColor(Color.parseColor("#3CB043"));viewStudent();
            viewStatus = 0;
            textListTitle.setVisibility(View.VISIBLE);
            textSearch.setVisibility(View.VISIBLE);
            btnSearch.setVisibility(View.VISIBLE);
        }
    };

    private View.OnClickListener searchStudentOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String keyword = textSearch.getText().toString();
            viewSearchStudent(keyword);
        }
    };

    private View.OnClickListener viewInstrOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            viewInstructor();
            btnAddStudent.setText("add instr");
            btnAddStudent.setBackgroundColor(Color.parseColor("#FFFF00"));
            btnAddStudent.setTextColor(Color.parseColor("#000000"));
            viewStatus = 1;

            textListTitle.setVisibility(View.GONE);
            textSearch.setVisibility(View.GONE);
            btnSearch.setVisibility(View.GONE);

        }
    };

    private View.OnClickListener viewPracticalOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            viewPractical();
            btnAddStudent.setText("add prac");
            btnAddStudent.setBackgroundColor(Color.parseColor("#87CEEB"));
            viewStatus = 2;
            textListTitle.setVisibility(View.GONE);
            textSearch.setVisibility(View.GONE);
            btnSearch.setVisibility(View.GONE);
        }
    };

    public void viewStudent() {
        data = new Data();
        data.load(this);
        //data.generateData(this);    // generate data
        data.getStudentList(role);

        StudentListAdapter studentListAdapter = new StudentListAdapter(this, data.tempUsers);
        studentRecyclerView.setAdapter(studentListAdapter);
        studentRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }



    public void viewSearchStudent(String keyword) {
        data = new Data();
        data.load(this);
        data.getSearchedList(role, keyword);

        StudentListAdapter studentListAdapter = new StudentListAdapter(this, data.tempUsers);
        studentRecyclerView.setAdapter(studentListAdapter);
        studentRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        for (int i = 0; i < data.tempUsers.size(); i++) {
            Log.d(TAG, "viewSearchStudent: tempUsers(" + i + ") name : " + data.tempUsers.get(i).getName());
        }
        Log.d(TAG, "viewSearchStudent: size of list : " + data.tempUsers.size());
    }

    public void viewInstructor() {
        data = new Data();
        data.load(this);
        data.getInstructors();

        InstructorListAdapter instructorListAdapter = new InstructorListAdapter(this, data.tempUsers);
        studentRecyclerView.setAdapter(instructorListAdapter);
        studentRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    public void viewPractical() {
        data = new Data();
        data.load(this);

        StaffPracticalListAdapter staffPracticalListAdapter = new StaffPracticalListAdapter(this, data.practicals);
        studentRecyclerView.setAdapter(staffPracticalListAdapter);
        studentRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    public void addStudent() {
        Intent intent = new Intent(StaffActivity.this, CRUDStudentActivity.class);
        intent.putExtra("role", role);
        startActivity(intent);

    }

    public void addInstructor() {
        Intent intent = new Intent(StaffActivity.this, CRUDInstrActivity.class);
        intent.putExtra("mode", 0); // mode 0 = add functionality, mode 1 = edit & delete functionality
        intent.putExtra("role", role);
        startActivity(intent);
    }

    public void addPractical() {
        Intent intent = new Intent(StaffActivity.this, CRUDPracActivity.class);
        intent.putExtra("mode", 0);
        intent.putExtra("role", role);
        startActivity(intent);
    }


}