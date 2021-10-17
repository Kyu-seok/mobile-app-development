package com.yeumkyuseok.mathtest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.GeneralSecurityException;
import java.time.LocalDateTime;

import javax.net.ssl.HttpsURLConnection;

public class Test extends AppCompatActivity {

    private static final String TAG = "Test.java";
    // public static final String BASE_URL = "https://10.0.2.2:8000/random/question";
    public static final String BASE_URL = "https://192.168.219.104:8000/random/question";

    Student studentTaking;
    int markScored = 0, questionNumber = 1;
    LocalDateTime dateTime;
    JSONObject jsonObject;

    // R.layout.ready_state.xml
    Button btnStart, btnDelete;
    TextView txtName, txtEmail, txtPhone;

    // R.layout.question_normal_layout.xml
    Button btnPrev, btnNext, btnAns1, btnAns2, btnAns3, btnAns4, btnNormalQuit, btnNormalPass;
    TextView txtNormQuestionNum, txtNormQuestionQuestion, txtNormMark, txtNormPage, txtNormTime;

    // R.layout.question_blank_layout.xml
    Button btnBlankAnswer, btnBlankQuit, btnBlankPass;
    TextView txtBlankQuestionNum, txtBlankQuestionQuestion, txtBlankMark, txtBlankAnswer, txtBlankTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ready_state);

        txtName = (TextView) findViewById(R.id.textNameReady);
        txtEmail = (TextView) findViewById(R.id.textEmailReady);
        txtPhone = (TextView) findViewById(R.id.textPhoneReady);
        btnStart = (Button) findViewById(R.id.buttonStartFromReady);
        btnDelete = (Button) findViewById(R.id.buttonDeleteFromReady);

        Intent intent = getIntent();
        studentTaking = (Student) intent.getExtras().get("student");

        txtName.setText("Name : " + studentTaking.getFullName());
        txtEmail.setText("Email : " + studentTaking.getEmail());
        txtPhone.setText("Phone No : " + studentTaking.getPhoneNum());

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doTest();
            }
        });
    }

    private void doTest() {
        new DownloaderTask().execute();
    }

    private class DownloaderTask extends AsyncTask<Void, Integer, String> {
        private int totalBytes;

        @Override
        protected String doInBackground(Void... voids) {
            String text;
            try {
                String urlString = Uri.parse(BASE_URL)
                        .buildUpon()
                        .appendQueryParameter("method", "thedata.getit")
                        .appendQueryParameter("format", "json")
                        .appendQueryParameter("api_key", "01189998819991197253")
                        .build().toString();
                URL url = new URL(urlString);

                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                DownloadUtils.addCertificate(Test.this, (HttpsURLConnection) conn);

                totalBytes = conn.getContentLength();

                try {
                    Log.d(TAG, "Connecting");

                    int responseCode = conn.getResponseCode();
                    String responseMSG = conn.getResponseMessage();
                    if (responseCode != HttpsURLConnection.HTTP_OK) {
                        String msg = String.format("Error from Server: %d - %s", responseCode, responseMSG);
                        Log.d(TAG, msg);
                        throw new IOException(msg);
                    }
                    InputStream is = conn.getInputStream();
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();

                    int totalBytesRead = 0;
                    int bytesRead = 0;
                    byte[] buffer = new byte[1024];
                    while ((bytesRead = is.read(buffer)) > 0) {
                        baos.write(buffer, 0, bytesRead);
                        totalBytesRead += bytesRead;
                        publishProgress(totalBytesRead);
                    }
                    baos.close();
                    jsonObject = new JSONObject(new String(baos.toByteArray()));
                    return new String(baos.toByteArray());
                } finally {
                    Log.d(TAG, "Disconnect");
                    conn.disconnect();
                }

            } catch (GeneralSecurityException | IOException | JSONException e) {
                return e.getMessage();
            }

        }

        /*
        @Override
        protected void onProgressUpdate(Integer... progress) {
            progressBar.setIndeterminate(false);
            progressBar.setMax(totalBytes);
            progressBar.setProgress(progress[0]);
        }
        */

        @Override
        protected void onPostExecute(String result) {
            // textArea.setText(result);
            // progressBar.setVisibility(View.INVISIBLE);

            String question;
            JSONArray options;
            int givenTime, answer;

            Log.d(TAG, "onPostExecute: result :" + result);
            try {
                // do things here
                Log.d(TAG, "check JsonObject question: " + jsonObject.get("question"));
                Log.d(TAG, "check JsonObject result: " + jsonObject.get("result"));
                Log.d(TAG, "check JsonObject options: " + jsonObject.get("options"));
                Log.d(TAG, "check JsonObject timetosolve: " + jsonObject.get("timetosolve"));

                options = jsonObject.getJSONArray("options");

                if (options.length() > 0)  {
                    loadNormalQuestion(jsonObject);
                } else {
                    loadBlankQuestion(jsonObject);
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }


    }


    // implement all the functions here
    private void loadNormalQuestion(JSONObject jsonObject) {
        setContentView(R.layout.question_normal_layout);

        String questionContent;
        JSONArray options;
        int answer, timeToSolve, remainingTime;

        btnPrev = (Button) findViewById(R.id.buttonPrevAns);
        btnNext = (Button) findViewById(R.id.buttonNextAns);
        btnAns1 = (Button) findViewById(R.id.buttonAns1);
        btnAns2 = (Button) findViewById(R.id.buttonAns2);
        btnAns3 = (Button) findViewById(R.id.buttonAns3);
        btnAns4 = (Button) findViewById(R.id.buttonAns4);
        btnNormalQuit = (Button) findViewById(R.id.buttonNormalQuit);
        btnNormalPass = (Button) findViewById(R.id.buttonBlankQuestionPass);
        txtNormQuestionNum = (TextView) findViewById(R.id.textNormalQuestionNumber);
        txtNormQuestionQuestion = (TextView) findViewById(R.id.textNormalQuestionQuestion);
        txtNormMark = (TextView) findViewById(R.id.textNormalQuestionMark);
        txtNormPage = (TextView) findViewById(R.id.textNormalQuestionPage);
        txtNormTime = (TextView) findViewById(R.id.textNormalTime);

        try {
            questionContent = (String) jsonObject.get("question");
            options = jsonObject.getJSONArray("options");
            answer = jsonObject.getInt("result");
            timeToSolve = jsonObject.getInt("timetosolve");
            remainingTime = timeToSolve;


            txtNormQuestionNum.setText("Question Number " + questionNumber);
            txtNormQuestionQuestion.setText("Question: " + questionContent);
            txtNormMark.setText("Mark : " + markScored);
            txtNormTime.setText(remainingTime + " s");

            btnAns1.setText(options.getString(0));
            btnAns2.setText(options.getString(1));
            btnAns3.setText(options.getString(2));
            btnAns4.setText(options.getString(3));

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void loadBlankQuestion(JSONObject jsonObject) {
        setContentView(R.layout.question_blank_layout);
    }

}