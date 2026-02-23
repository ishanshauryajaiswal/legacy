package com.example.shaurya.moviebuff;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shaurya.moviebuff.Model.MoviePOJO;
import com.squareup.picasso.Picasso;

/**
 * Created by shaurya on 18/02/18.
 */

public class MovieDetailActivity extends AppCompatActivity {
    ImageView imgBackdrop, imgPoster;
    TextView tvTitle, tvDate, tvPopularity, tvOverview;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        MoviePOJO moviePOJO = (MoviePOJO) getIntent().getSerializableExtra("movie_detail");
        MovieDetailFragment movieDetailFragment = MovieDetailFragment.newInstance(moviePOJO);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container_movie_detail_fragment, movieDetailFragment)
                .commit();
    }
}
