package com.yeumkyuseok.pracgrader;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class CRUDResultActivity extends AppCompatActivity {

    int mode;   // ADD=0, EDIT=1
    String username;
    String pracTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        mode = intent.getExtras().getInt("mode");

        if (mode == 0) { // add
            setContentView(R.layout.activity_add_result);
        } else {        // delete
            setContentView(R.layout.activity_edit_result);
        }

        //set username
        username = "yura";

        // checkPracExists(String pracTitle)
        // if there is pracTitle,
        // get double data mark, and check if markScored is smaller than total mark able to score
        // insert the data into db


    }
}