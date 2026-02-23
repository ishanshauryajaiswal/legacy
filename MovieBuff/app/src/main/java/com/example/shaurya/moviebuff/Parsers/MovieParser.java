package com.example.shaurya.moviebuff.Parsers;

import com.example.shaurya.moviebuff.Model.MoviePOJO;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by shaurya on 17/02/18.
 */

public class MovieParser {
    private String data;

    public MovieParser(String data){
        this.data = data;
    }

    public ArrayList<MoviePOJO> getData(){
        ArrayList<MoviePOJO> movies = new ArrayList<MoviePOJO>();
        try {
            JSONObject jsonObject = new JSONObject(data);
            JSONArray results = jsonObject.getJSONArray("results");
            for (int i=0; i< results.length(); i++){
                JSONObject j = results.getJSONObject(i);
                MoviePOJO moviePOJO = new MoviePOJO();
                moviePOJO.setId(j.getInt("id"));
                moviePOJO.setTitle(j.getString("title"));
                moviePOJO.setVote_average((float) j.getDouble("vote_average"));
                moviePOJO.setPoster_path(j.getString("poster_path"));
                moviePOJO.setBackdrop_path(j.getString("backdrop_path"));
                moviePOJO.setOverview(j.getString("overview"));
                moviePOJO.setRelease_date(j.getString("release_date"));
                movies.add(moviePOJO);
            }
        }
        catch (JSONException e){
            e.printStackTrace();
        }
        return movies;
    }
}
