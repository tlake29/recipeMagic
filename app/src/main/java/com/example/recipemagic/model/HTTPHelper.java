package com.example.recipemagic.model;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

/**
* This class is designed to read assist in opening the JSON files
* provided in the url and reading them into a string.
*/
public class HTTPHelper {

    /**
     * This function is designed to read the JSON file from the API database.
     * @param url
     * @return
     */
    public String readHTTP(String url) {
        try {
            URL urlObj = new URL(url);
            HttpsURLConnection connection = (HttpsURLConnection) urlObj.openConnection();
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder data = new StringBuilder();
            String line;
            do {
                line = reader.readLine();
                if (line != null) {
                    data.append(line);
                }
            }
            while (line != null);
            return data.toString();
        } catch (IOException ioe) {
            Log.e("HTTPHelper", "Error reading from file (" + url + "): " + ioe);
            return null;
        }
    }
}
