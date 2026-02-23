package com.example.shaurya.moviebuff.Model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by shaurya on 10/02/18.
 */

public class MoviePOJO implements Serializable {
    public String title;
    public String poster_path;
    public String backdrop_path;
    public String overview;
    public String release_date;
    public float vote_average;
    public int id;


    public int getId() {return id;}

    public void setId(int id) {this.id = id;}

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPoster_path() {
        return "http://image.tmdb.org/t/p/w185"+poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public String getBackdrop_path() {
        return "http://image.tmdb.org/t/p/w500"+backdrop_path;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public float getVote_average() {
        return vote_average;
    }

    public void setVote_average(float vote_average) {
        this.vote_average = vote_average;
    }
}
