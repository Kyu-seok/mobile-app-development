package com.yeumkyuseok.questionreciever;

import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.GeneralSecurityException;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    public static final String BASE_URL = "https://10.0.2.2:8000/random/question";
    // public static final String BASE_URL = "https://192.168.219.107:8000/random/question";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new DownloaderTask().execute();
    }

    private class DownloaderTask extends AsyncTask<Void, Integer, String> {
        private int totalBytes;
        JSONObject jsonObject;

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
                DownloadUtils.addCertificate(MainActivity.this, (HttpsURLConnection) conn);

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
            Log.d(TAG, "onPostExecute: result :" + result);
            try {
                Log.d(TAG, "check JsonObject question: " + jsonObject.get("question"));
                Log.d(TAG, "check JsonObject result: " + jsonObject.get("result"));
                Log.d(TAG, "check JsonObject options: " + jsonObject.get("options"));
                Log.d(TAG, "check JsonObject timetosolve: " + jsonObject.get("timetosolve"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}