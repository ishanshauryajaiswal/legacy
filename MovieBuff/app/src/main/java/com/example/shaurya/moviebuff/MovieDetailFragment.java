package com.example.shaurya.moviebuff;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shaurya.moviebuff.Database.MovieDBHelper;
import com.example.shaurya.moviebuff.Model.MoviePOJO;
import com.squareup.picasso.Picasso;

/**
 * Created by shaurya on 18/02/18.
 */

public class MovieDetailFragment extends Fragment {
    ImageView imgBackdrop, imgPoster;
    TextView tvTitle, tvDate, tvPopularity, tvOverview;
    FloatingActionButton fab;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movie_detail, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews();
        final MoviePOJO moviePOJO = (MoviePOJO) getArguments().get("movie_detail");
        Picasso.with(getActivity()).setLoggingEnabled(true);
        Picasso.with(getContext())
                .load(moviePOJO.getBackdrop_path())
                .placeholder(R.color.colorAccent)
                .into(imgBackdrop);
        Picasso.with(getContext())
                .load(moviePOJO.getPoster_path())
                .placeholder(R.color.colorAccent)
                .into(imgPoster);
        tvOverview.setText(moviePOJO.getOverview());
        tvDate.setText(moviePOJO.getRelease_date());
        tvPopularity.setText(moviePOJO.getVote_average()+"");
        tvTitle.setText(moviePOJO.getTitle());
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fabClicked(moviePOJO);
            }
        });
    }

    private void initViews() {
        Activity context = getActivity();
        imgBackdrop = (ImageView)context.findViewById(R.id.movie_detail_backdrop_image);
        imgPoster = (ImageView)context.findViewById(R.id.movie_detail_poster_path__image);
        tvTitle = (TextView)context.findViewById(R.id.movie_detail_title_text);
        tvDate = (TextView)context.findViewById(R.id.movie_detail_release_date_text);
        tvPopularity = (TextView)context.findViewById(R.id.movie_detail_vote_average_text);
        tvOverview = (TextView)context.findViewById(R.id.movie_detail_overview_text);
        fab = (FloatingActionButton)context.findViewById(R.id.fab);
    }

    public void fabClicked(MoviePOJO movie){
        MovieDBHelper m = MovieDBHelper.getInstance(getActivity());
        m.addMovie(movie);
    }

    public static MovieDetailFragment newInstance(MoviePOJO moviePOJO){
        MovieDetailFragment movieDetailFragment = new MovieDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("movie_detail", moviePOJO);
        movieDetailFragment.setArguments(bundle);
        return movieDetailFragment;
    }
}
