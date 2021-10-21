package com.yeumkyuseok.jsonimageexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Intent intent = getIntent();

        String imageUrl = intent.getExtras().get("url").toString();


        ImageView imageView = findViewById(R.id.imageView_second);

        Picasso.get().load(imageUrl).into(imageView);
    }
}