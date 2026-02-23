package com.example.shaurya.moviebuff.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.shaurya.moviebuff.Model.MoviePOJO;

import java.util.ArrayList;


/**
 * Created by shaurya on 20/02/18.
 */

public class MovieDBHelper extends SQLiteOpenHelper {
    private static MovieDBHelper sInstance;

    private final String TAG = "MOVIEDBHELPER";
    // Database Info
    private static final String DATABASE_NAME = "moviesDatabase";
    private static final int DATABASE_VERSION = 1;

    // Table Names
    private static final String TABLE_MOVIES = "movies";

    // Movie Table Columns
    private static final String KEY_MOVIE_ID = "id";
    private static final String KEY_MOVIE_TITLE = "title";
    private static final String KEY_MOVIE_POSTER_PATH = "poster_path";
    private static final String KEY_MOVIE_RELEASE_DATE = "release_date";
    private static final String KEY_MOVIE_OVERVIEW = "overview";
    private static final String KEY_MOVIE_BACKDROP_PATH = "backdrop_path";
    private static final String KEY_MOVIE_VOTE_AVERAGE = "vote_average";

    private MovieDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static synchronized MovieDBHelper getInstance(Context context) {
        // Use the application context, which will ensure that you
        // don't accidentally leak an Activity's context.
        // See this article for more information: http://bit.ly/6LRzfx
        if (sInstance == null) {
            sInstance = new MovieDBHelper(context.getApplicationContext());
        }
        return sInstance;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_MOVIE_TABLE = "CREATE TABLE " + TABLE_MOVIES +
                "(" +
                KEY_MOVIE_ID + " INTEGER PRIMARY KEY," + // Define a primary key
                KEY_MOVIE_TITLE + " TEXT, " +
                KEY_MOVIE_POSTER_PATH + " TEXT, " +
                KEY_MOVIE_OVERVIEW + " TEXT, " +
                KEY_MOVIE_BACKDROP_PATH + " TEXT, " +
                KEY_MOVIE_RELEASE_DATE + " TEXT," +
                KEY_MOVIE_VOTE_AVERAGE + " REAL" +
                ")";

        sqLiteDatabase.execSQL(CREATE_MOVIE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        if (i != i1) {
            // Simplest implementation is to drop all old tables and recreate them
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_MOVIES);
            onCreate(db);
        }
    }

    public void addMovie(MoviePOJO m){
        SQLiteDatabase db = getWritableDatabase();
        db.beginTransaction();
        try {
            ContentValues values = new ContentValues();
            values.put(KEY_MOVIE_ID, m.getId());
            values.put(KEY_MOVIE_TITLE, m.getTitle());
            values.put(KEY_MOVIE_POSTER_PATH, m.poster_path);
            values.put(KEY_MOVIE_BACKDROP_PATH, m.backdrop_path);
            values.put(KEY_MOVIE_OVERVIEW, m.getOverview());
            values.put(KEY_MOVIE_RELEASE_DATE, m.getRelease_date());
            values.put(KEY_MOVIE_VOTE_AVERAGE, m.getVote_average());
            int rows = db.update(TABLE_MOVIES, values, KEY_MOVIE_ID + " = " + m.getId(), null);
            if (rows == 0)
                db.insertOrThrow(TABLE_MOVIES, null, values);
            db.setTransactionSuccessful();

        }catch (Exception e) {
            Log.d(TAG, "Error while trying to add movie to database");
        } finally {
            db.endTransaction();
        }
    }

    public ArrayList<MoviePOJO> getSavedMovies(){
        ArrayList<MoviePOJO> movies = new ArrayList<MoviePOJO>();
        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_MOVIES;
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                MoviePOJO movie = new MoviePOJO();
                movie.setId(cursor.getInt(0));
                movie.setTitle(cursor.getString(1));
                movie.setPoster_path(cursor.getString(2));
                movie.setOverview(cursor.getString(3));
                movie.setBackdrop_path(cursor.getString(4));
                movie.setRelease_date(cursor.getString(5));
                movie.setVote_average(cursor.getFloat(6));
                movies.add(movie);
                cursor.moveToNext();
            }
        }
        return movies;
    }

}
