package com.yeumkyuseok.tutorial5;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_THUMBNAIL = 1;
    private static final int REQUEST_CONTACT = 2;
    private Intent callIntent, mapIntent, thumbnailPhotoIntent, contactIntent;
    private Button callButton, mapButton, camButton, contactButton;
    private EditText contactNumber, mapLatitude, mapLongitude;
    private ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        callButton = (Button) findViewById(R.id.callButtonButton);
        mapButton = (Button) findViewById(R.id.showLocationButtonButton);
        iv = (ImageView) findViewById(R.id.imageView);
        contactButton = (Button) findViewById(R.id.contactButton);
        // callIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:0123456789"));
        // mapIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:-32.0062, 115.8944"));

        camButton = (Button) findViewById(R.id.cameraButton);

        callButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callNumber();
            }
        });

        mapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getMapLocation();
            }
        });

        camButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getCampButton();
            }
        });

        contactButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getContact();
            }
        });

    }

    private String getContactNumber() {
        contactNumber = (EditText) findViewById(R.id.telNumber);
        return contactNumber.getText().toString();
    }

    private void callNumber() {
        callIntent = new Intent(Intent.ACTION_DIAL);
        callIntent.setData(Uri.parse("tel:" + getContactNumber()));
        startActivity(callIntent);
    }

    private void  getMapLocation() {
        mapLatitude = (EditText) findViewById(R.id.mapLatitude);
        mapLongitude = (EditText) findViewById(R.id.mapLongitude);
        mapIntent = new Intent(Intent.ACTION_VIEW);
        mapIntent.setData(Uri.parse(String.format("geo:%f, %f",
                Double.parseDouble(mapLatitude.getText().toString()),
                Double.parseDouble(mapLongitude.getText().toString())
                )));
        startActivity(mapIntent);
    }

    private void getCampButton() {
        thumbnailPhotoIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(thumbnailPhotoIntent, REQUEST_THUMBNAIL);
    }

    private void getContact() {
        contactIntent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
        startActivityForResult(contactIntent, REQUEST_CONTACT);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent resultIntent) {
        super.onActivityResult(requestCode, resultCode, resultIntent);
        if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_THUMBNAIL) {
            Bitmap thumbnail = (Bitmap) resultIntent.getExtras().get("data");
            iv.setImageBitmap(thumbnail);
        }
    }

}














