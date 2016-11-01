package com.example.lab_05;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class DownloadLicense extends AsyncTask<String, Void, String>{

    @Override
    public String doInBackground(String... params) {

        String license = "";
        try {
            URL url = new URL("https://www.gnu.org/licenses/gpl.txt");
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            int result = conn.getResponseCode();
            if (result == HttpURLConnection.HTTP_OK) {
                InputStream comeIn = conn.getInputStream();
                BufferedReader buffRead = new BufferedReader(new InputStreamReader(comeIn));
                String line;
                while ((line = buffRead.readLine()) != null) {
                    license += line + "\n";
                }
            }
        } catch (MalformedURLException e) {
            Log.d("HTTPUrlConnectionTest", "Malformed URL Exception.");
        } catch (IOException e) {
            Log.d("HTTPUrlConnectionTest", "IO Exception.");
        }
        return license;
    }
}
