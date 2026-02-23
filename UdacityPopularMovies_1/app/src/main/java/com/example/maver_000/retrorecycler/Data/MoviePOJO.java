package com.example.maver_000.retrorecycler.Data;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by maver_000 on 4/5/2016.
 */
public class MoviePOJO implements Parcelable{

    public String original_title;
    public String title;
    public String poster_path;
    public String backdrop_path;
    public String overview;
    public String release_date;
    public float vote_average;

    public MoviePOJO()
    {

    }
    public MoviePOJO(Parcel in)
    {
        super();
        original_title=in.readString();
        title=in.readString();
        poster_path=in.readString();
        backdrop_path=in.readString();
        overview=in.readString();
        release_date=in.readString();
        vote_average=in.readFloat();

    }

    public static final Creator<MoviePOJO> CREATOR=new Parcelable.Creator<MoviePOJO>() {
        @Override
        public MoviePOJO createFromParcel(Parcel source) {
            return new MoviePOJO(source);
        }
        @Override
        public MoviePOJO[] newArray(int size) {
            return new MoviePOJO[size];
        }
    };

    @Override
    public int describeContents()
    {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel,int i){
        parcel.writeString(original_title);
        parcel.writeString(title);
        parcel.writeString(poster_path);
        parcel.writeString(backdrop_path);
        parcel.writeString(overview);
        parcel.writeString(release_date);
        parcel.writeFloat(vote_average);
    }


    public String getOriginal_title(){
        return original_title;
    }
    public String getTitle(){
        return title;
    }
    public String getPoster_path(){
       return "http://image.tmdb.org/t/p/w185"+poster_path;
    }
    public String getBackdrop_path() {
        return "http://image.tmdb.org/t/p/w500"+backdrop_path;
    }

    public String getOverview() {
        return overview;
    }

    public String getRelease_date() {
        return release_date;
    }

    public float getVote_average() {
        return vote_average;
    }
}
