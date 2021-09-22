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

import java.util.ArrayList;
import java.util.List;

public class CRUDResultActivity extends AppCompatActivity {
    private static final String TAG = "CRUDResultActivity";

    int mode;   // ADD=0, EDIT=1
    String username;
    String pracTitle, loadPracTitle;
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

            TextView textName = findViewById(R.id.textAddResultUsername);
            TextView textEmail = findViewById(R.id.textAddResultEmail);
            TextView textCountry = findViewById(R.id.textAddResultCountry);
            TextView textPracTitle = findViewById(R.id.editTextPracticalTitle);
            TextView textMarkScored = findViewById(R.id.editTextMarkScoredAdd);
            Button btnCheck = findViewById(R.id.btnCheckAddTitle);
            Button btnAddResult = findViewById(R.id.btnAddResult);

            User user;
            user = data.getStudent(username);
            Log.d(TAG, "onCreate: user name: " + user.getUser_name());
            Log.d(TAG, "onCreate: user email: " + user.getEmail());
            Log.d(TAG, "onCreate: user country: " + user.getCountry());

            textName.setText(user.getName());
            textEmail.setText("Email : " + user.getEmail());
            textCountry.setText("Country : " + user.getCountry());

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
                        if (checkValidInput(pracTitle, input)) {
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

        } else {        // edit         -------------------------------
            setContentView(R.layout.activity_edit_result);

            double currMark, maxMark;

            loadPracTitle = intent.getExtras().get("pracTitle").toString();
            currMark = intent.getExtras().getDouble("currMark");
            maxMark = data.getPracMarkAvailable(loadPracTitle);

            TextView textName = findViewById(R.id.textEditResultUsername);
            TextView textEmail = findViewById(R.id.textEditResultEmail);
            TextView textCountry = findViewById(R.id.textEditResultCountry);
            TextView textCurrMark = findViewById(R.id.textCurrMark);
            TextView editMark = findViewById(R.id.editTextMarkScored);
            Button buttonAddResult = findViewById(R.id.btnAddResult);
            Button btnDelete  = findViewById(R.id.btnDeleteResult);

            User user;
            user = data.getStudent(username);

            textName.setText(user.getName());
            textEmail.setText("Email : " + user.getEmail());
            textCountry.setText("Country : " + user.getCountry());
            textCurrMark.setText("Current Mark : " + currMark + " / " + maxMark);


            buttonAddResult.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String input = editMark.getText().toString();
                    if (input.isEmpty()) {
                        Toast.makeText(CRUDResultActivity.this, "Please Insert a value", Toast.LENGTH_SHORT).show();
                    } else {
                        if (checkValidInput(loadPracTitle, input)) {
                            double mark = Double.parseDouble(input);
                            data.editTakePrac(CRUDResultActivity.this, username, loadPracTitle, mark);
                            Toast.makeText(CRUDResultActivity.this, "Successfully edited practical taken", Toast.LENGTH_SHORT).show();
                            Intent intent1 = new Intent(CRUDResultActivity.this, SingleStudentActivity.class);
                            intent1.putExtra("username", username);
                            startActivity(intent1);

                        }

                    }
                }
            });

            btnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    data.deleteTakenPrac(CRUDResultActivity.this, username, loadPracTitle);
                    Toast.makeText(CRUDResultActivity.this, "deleted Taken Practical", Toast.LENGTH_SHORT).show();
                    Intent intent1 = new Intent(CRUDResultActivity.this, SingleStudentActivity.class);
                    intent1.putExtra("username", username);
                    startActivity(intent1);
                }
            });

        }


    }

    private boolean checkValidInput(String pracTitle, String input) {
        boolean isValid = false;
        try {
            double value = Double.parseDouble(input);
            double maxValue = data.getPracMarkAvailable(pracTitle);
            if ((value >= 0) && (value <= maxValue)) {
                return true;
            } else {
                Toast.makeText(CRUDResultActivity.this, "Please enter positive value =< " + maxValue, Toast.LENGTH_SHORT).show();
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