package com.example.shaurya.moviebuff.Tasks;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.ProgressBar;

import com.example.shaurya.moviebuff.OnTaskCompleted;
import com.example.shaurya.moviebuff.Parsers.MovieParser;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by shaurya on 10/02/18.
 */

public class GetMoviesTask extends AsyncTask<String , Void, String > {

    public OnTaskCompleted onTaskCompleted;
    int page;
    public GetMoviesTask(int page, OnTaskCompleted onTaskCompleted){
        this.onTaskCompleted = onTaskCompleted;
        this.page = page;
    }

    @Override
    public void onPreExecute() {
        super.onPreExecute();
        onTaskCompleted.onTaskStarted();
    }

    @Override
    public String doInBackground(String... strings) {
        String result = null;
        try {
            URL url = new URL(strings[0]+page);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            result = inputStreamToString(in);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.d("response", result);

        return result;
    }

    @Override
    public void onPostExecute(String s) {
        super.onPostExecute(s);
        onTaskCompleted.onTaskCompleted(s);
    }

    private String inputStreamToString(InputStream is) {
        String rLine = "";
        StringBuilder answer = new StringBuilder();
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader rd = new BufferedReader(isr);
        try {
            while ((rLine = rd.readLine()) != null) {
                answer.append(rLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return answer.toString();
    }
}
