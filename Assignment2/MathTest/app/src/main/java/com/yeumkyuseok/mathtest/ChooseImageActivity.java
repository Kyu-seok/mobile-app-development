package com.yeumkyuseok.mathtest;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.BlockingDeque;

public class ChooseImageActivity extends AppCompatActivity {
    private static final String TAG = "ChooseImageActivity";

    static final int REQUEST_IMAGE_CAPTURE = 1;
    String currentPhotoPath;

    Student student;
    Data data;
    Button btnTakePhoto, btnLoadStorage, btnBrowseOnline;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_image);
        data = new Data();
        data.load(this);

        Intent intent = getIntent();
        student = (Student) intent.getExtras().get("student");

        btnTakePhoto = (Button) findViewById(R.id.buttonTakeLivePhoto);
        btnLoadStorage = (Button) findViewById(R.id.buttonLoadStorage);
        btnBrowseOnline = (Button) findViewById(R.id.buttonBrowsePhotoOnline);

        // TODO : set onClickListener for each of three buttons and load to next intent or Activity

        btnTakePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dispatchTakePictureIntent();
                galleryAddPic();
                Log.d(TAG, "onClick: currentPhotoPath : " + currentPhotoPath);
                Log.d(TAG, "onClick: student : " + student.getFullName());
                student.setPhoto(currentPhotoPath);
                data.addStudent(student);
                //data.addPhotoToStudent(student, currentPhotoPath);
            }
        });

        btnLoadStorage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChooseImageActivity.this, LoadImage.class);
                intent.putExtra("student", student);
                startActivity(intent);
            }
        });

        btnBrowseOnline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChooseImageActivity.this, BrowseImage.class);
                intent.putExtra("student", student);
                startActivity(intent);
            }
        });
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
                Log.d(TAG, "dispatchTakePictureIntent: photoFile : " + photoFile);
            } catch (IOException ex) {
                // Error occurred while creating the File
                Log.d(TAG, "dispatchTakePictureIntent: ERROR");
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(this,
                        "com.example.android.fileprovider",
                        photoFile);
                Log.d(TAG, "dispatchTakePictureIntent: photoURI : " + photoURI);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            // Bundle extras = data.getExtras();
            // Bitmap imageBitmap = (Bitmap) extras.get("data");
            Bitmap bitmap = BitmapFactory.decodeFile(currentPhotoPath);
            // imageView.setImageBitmap(bitmap);
            Intent intent = new Intent(ChooseImageActivity.this, MainActivity.class);
            startActivity(intent);
        }
    }

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file : path for use with Action_View intents
        currentPhotoPath = image.getAbsolutePath();
        return image;
    }

    private void galleryAddPic() {
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        File f = new File(currentPhotoPath);
        Uri contentUri = Uri.fromFile(f);
        mediaScanIntent.setData(contentUri);
        this.sendBroadcast(mediaScanIntent);
    }
}