package com.example.maver_000.retrorecycler.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.maver_000.retrorecycler.Data.MoviePOJO;
import com.example.maver_000.retrorecycler.R;
import com.squareup.picasso.Picasso;

/**
 * Created by maver_000 on 4/8/2016.
 */
public class MovieDetailActivity extends AppCompatActivity {
    TextView movie_detail_title,movie_detail_release_date,movie_detail_vote_average,movie_detail_overview;
    ImageView image_detail_poster_path;
    ImageView image_detail_backdrop_path;
    MoviePOJO temp=new MoviePOJO();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.coordinator_layout);
        if(getIntent().hasExtra("movie_detail")){
            temp=(MoviePOJO) getIntent().getExtras().get("movie_detail");
        }
        else
        {
            Toast.makeText(getApplicationContext(),"Something's Wrong",Toast.LENGTH_SHORT).show();
        }
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(temp.title);
        image_detail_poster_path=(ImageView)findViewById(R.id.movie_detail_poster_path__image);
        image_detail_backdrop_path =(ImageView)findViewById(R.id.movie_detail_backdrop_image);
        movie_detail_title=(TextView)findViewById(R.id.movie_detail_title_text);
        movie_detail_release_date=(TextView)findViewById(R.id.movie_detail_release_date_text);
        movie_detail_vote_average=(TextView)findViewById(R.id.movie_detail_vote_average_text);
        movie_detail_overview=(TextView)findViewById(R.id.movie_detail_overview_text);
        movie_detail_release_date.setText(getString(R.string.release_date).concat(temp.getRelease_date()));
        movie_detail_vote_average.setText(getString(R.string.average).concat(String.valueOf(temp.getVote_average())));
        movie_detail_overview.setText(temp.getOverview());
        movie_detail_title.setText(temp.getTitle());
        load_images();
    }

    public void load_images()
    {
        Picasso.with(getApplicationContext())
                .load(temp.getBackdrop_path())
                .placeholder(R.color.colorAccent)
                .into(image_detail_backdrop_path);
        Picasso.with(getApplicationContext())
                .load(temp.getPoster_path())
                .placeholder(R.color.colorAccent)
                .into(image_detail_poster_path);
    }
}
