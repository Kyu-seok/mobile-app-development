package com.yeumkyuseok.recyclerviewpractice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {

    ImageView mainImageView;
    TextView title, description;

    String name, desc;
    // int myImage      (for getting image number and using that int to find the location of image in the array)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        mainImageView = findViewById(R.id.imageView);
        title = findViewById(R.id.textTitle);
        description = findViewById(R.id.textSecDesc);

        getData();
        setData();

    }

    private void getData() {
        if (getIntent().hasExtra("imgAvatar") && getIntent().hasExtra("mName") && getIntent().hasExtra("mDesc")) {

            name = getIntent().getStringExtra("mName");
            desc = getIntent().getStringExtra("mDesc");


        } else {
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        }
    }

    private void setData() {
        title.setText(name);
        description.setText(desc);
        mainImageView.setImageResource(R.mipmap.ic_launcher_round);
    }
}