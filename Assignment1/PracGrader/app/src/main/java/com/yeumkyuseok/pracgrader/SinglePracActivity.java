package com.yeumkyuseok.pracgrader;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class SinglePracActivity extends AppCompatActivity {

    TextView textTitle, textMark, textDesc;
    Button btnConfirm, btnDelete;
    String title, desc;
    double mark;
    Data data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_prac);

        data = new Data();
        data.load(this);

        textTitle = findViewById(R.id.editTextSinglePracTitle);
        textMark = findViewById(R.id.editTextSinglePracMark);
        textDesc = findViewById(R.id.editTextSinglePracDesc);
        btnConfirm = findViewById(R.id.buttonSinglePracConfirm);
        btnDelete = findViewById(R.id.buttonSinglePracDelete);

        Intent intent = getIntent();
        title = intent.getExtras().get("title").toString();

        Practical practical = data.getPractical(title);

        textTitle.setText(practical.getTitle());
        textMark.setText(Double.toString(practical.getMark()));
        textDesc.setText(practical.getDescription());

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadData();
                if (allHasData()) {
                    Practical newPrac = new Practical(title, desc, mark);
                    data.editPrac(SinglePracActivity.this, title, newPrac);
                    Toast.makeText(SinglePracActivity.this, "Successfully Edited", Toast.LENGTH_SHORT).show();
                    Intent intent1 = new Intent(SinglePracActivity.this, StaffActivity.class);

                    startActivity(intent1);
                }
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data.deletePrac(practical);
                Toast.makeText(SinglePracActivity.this, "Successfully Deleted", Toast.LENGTH_SHORT).show();
                Intent intent1 = new Intent(SinglePracActivity.this, StaffActivity.class);
                startActivity(intent1);
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