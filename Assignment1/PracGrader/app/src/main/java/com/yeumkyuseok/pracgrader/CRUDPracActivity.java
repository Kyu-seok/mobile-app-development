package com.yeumkyuseok.pracgrader;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CRUDPracActivity extends AppCompatActivity {

    TextView textTitle, textMark, textDesc;
    Button addButton;
    String title, desc;
    double mark;
    Data data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crudprac);

        data = new Data();
        data.load(this);

        textTitle = findViewById(R.id.editTextPracTitle);
        textMark = findViewById(R.id.editTextPracMarkWeigh);
        textDesc = findViewById(R.id.editTextPracDescription);
        addButton = findViewById(R.id.buttonAddPractical);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadData();
                if (allHasData()) {
                    if (uniquePracTitle(title) && correctMarkFormat(mark)) {
                        data.addPrac(new Practical(title, desc, mark));
                        Toast.makeText(CRUDPracActivity.this, "Successfully Added", Toast.LENGTH_SHORT).show();
                        Intent intent1 = new Intent(CRUDPracActivity.this, StaffActivity.class);

                        startActivity(intent1);
                    }
                }
            }
        });
    }

    private void loadData() {
        if (!allHasData()) {
            Toast.makeText(this, "fill the blank", Toast.LENGTH_SHORT).show();
        }
        this.title = loadTitle();
        this.desc = loadDesc();
        this.mark = loadMark();
    }


    private boolean correctMarkFormat(double mark) {
        boolean isCorrect = false;

        if (mark >= 0) {
            if (mark <= 100) {
                isCorrect = true;
            } else {
                Toast.makeText(this, "number too big", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Enter real number", Toast.LENGTH_SHORT).show();
        }

        return isCorrect;
    }

    private boolean uniquePracTitle(String title) {
        boolean isUnique = false;
        if (data.checkUniquePrac(this, title)) {
            isUnique = true;
        } else {
            Toast.makeText(this, "Username already exists", Toast.LENGTH_SHORT).show();
        }
        return isUnique;
    }

    private boolean allHasData() {
        boolean allHasData = false;
        String mTitle, mDesc;
        double mMark;

        mTitle = loadTitle();
        mDesc = loadDesc();
        mMark = loadMark();

        if (!mTitle.isEmpty() && (!mDesc.isEmpty()) && (!(Double.toString(mMark).isEmpty()))) {
            allHasData = true;
        }
        return allHasData;
    }

    public String loadTitle() {
        return textTitle.getText().toString();
    }

    public String loadDesc() {
        return textDesc.getText().toString();
    }

    public double loadMark() {
        try {
            return Double.parseDouble(textMark.getText().toString());
        } catch (NumberFormatException e) {
            Toast.makeText(this, "no input", Toast.LENGTH_SHORT).show();
            return -1.0;
        }
    }

}