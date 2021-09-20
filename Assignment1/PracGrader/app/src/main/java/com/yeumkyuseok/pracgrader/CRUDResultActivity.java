package com.yeumkyuseok.pracgrader;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class CRUDResultActivity extends AppCompatActivity {

    int mode;   // ADD=0, EDIT=1
    String username;
    String pracTitle;
    Boolean isChecked;
    Data data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        mode = intent.getExtras().getInt("mode");
        username = intent.getExtras().get("username").toString();
        data = new Data();
        data.load(this);

        if (mode == 0) { // add
            setContentView(R.layout.activity_add_result);
            boolean checked;
            double markScored;

            TextView textPracTitle = findViewById(R.id.editTextPracticalTitle);
            TextView textMarkScored = findViewById(R.id.editTextMarkScoredAdd);
            Button btnCheck = findViewById(R.id.btnCheckAddTitle);
            Button btnAddResult = findViewById(R.id.btnAddResult);

            btnCheck.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        String input = textPracTitle.getText().toString();
                        pracTitle = input;
                        if (data.hasPractical(input)) {
                            isChecked = true;
                            btnCheck.setText("CHECKED");
                            btnCheck.setBackgroundColor(Color.parseColor("#3CB043"));
                        } else {
                            Toast.makeText(CRUDResultActivity.this, "Wrong format", Toast.LENGTH_SHORT).show();
                        }
                    } catch (ClassCastException e) {
                        Toast.makeText(CRUDResultActivity.this, "Wrong format", Toast.LENGTH_SHORT).show();
                    }
                }
            });

            btnAddResult.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String input = textMarkScored.getText().toString();
                    if (input.isEmpty()) {
                        Toast.makeText(CRUDResultActivity.this, "Please Insert a value", Toast.LENGTH_SHORT).show();
                    } else {
                        if (checkValidInput(input)) {
                            if (isChecked && (pracTitle.equals(textPracTitle.getText().toString()))) {
                                if (!pracTakenExists(username, pracTitle)) {
                                    double mark = Double.parseDouble(input);
                                    TakenPrac newTakenPrac = new TakenPrac(username, pracTitle, mark);
                                    data.addTakenPrac(newTakenPrac);
                                    Toast.makeText(CRUDResultActivity.this, "Successfully added practical taken", Toast.LENGTH_SHORT).show();
                                    Intent intent1 = new Intent(CRUDResultActivity.this, SingleStudentActivity.class);
                                    intent1.putExtra("username", username);
                                    startActivity(intent1);
                                } else {
                                    Toast.makeText(CRUDResultActivity.this, "this practical is already taken", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(CRUDResultActivity.this, "Re-Check practical title", Toast.LENGTH_SHORT).show();
                                btnCheck.setBackgroundColor(Color.parseColor("#6a0dad"));
                                btnCheck.setText("Check title");
                                isChecked = false;

                            }
                        } else {
                            Toast.makeText(CRUDResultActivity.this, "input is not valid", Toast.LENGTH_SHORT).show();
                            btnCheck.setBackgroundColor(Color.parseColor("#6a0dad"));
                            btnCheck.setText("Check title");
                            isChecked = false;

                        }
                    }
                }
            });



        } else {        // edit
            setContentView(R.layout.activity_edit_result);

        }


    }

    private boolean checkValidInput(String input) {
        boolean isValid = false;
        try {
            double value = Double.parseDouble(input);
            double maxValue = data.getPracMarkAvailable(pracTitle);
            if ((value >= 0) && (value <= maxValue)) {
                return true;
            } else {
                Toast.makeText(CRUDResultActivity.this, "Invalid value range", Toast.LENGTH_SHORT).show();
                return false;
            }
        } catch (NumberFormatException ignore) {
            return false;
        }
    }

    private boolean pracTakenExists(String username, String pracTitle) {
        boolean pracTakenExists = false;
        List<TakenPrac> takenPracList = new ArrayList<>();
        takenPracList = data.takenPracs;
        for (int i = 0; i < takenPracList.size(); i++) {
            TakenPrac pracTaken = takenPracList.get(i);
            if (pracTaken.getUsername().equals(username) && pracTaken.getPracTitle().equals(pracTitle)) {
                pracTakenExists = true;
            }
        }
        return pracTakenExists;
    }
}