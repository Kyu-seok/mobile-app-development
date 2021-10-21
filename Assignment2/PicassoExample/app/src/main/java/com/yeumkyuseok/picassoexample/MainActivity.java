package com.yeumkyuseok.picassoexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import com.squareup.picasso.Downloader;
import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView imageView = findViewById(R.id.image_view);

        String URLs[] = {
                ""
        };

        // String url = "https://cdn.pixabay.com/photo/2016/05/07/09/40/social-media-1377251_960_720.png";
        Picasso.get().load("https://www.flickr.com/services/feeds/photos_public.gne").into(imageView);

        //Picasso.get().load(url).into(imageView);

    }
}