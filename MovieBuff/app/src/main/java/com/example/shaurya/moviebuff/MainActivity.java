package com.example.shaurya.moviebuff;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import com.example.shaurya.moviebuff.Database.MovieDBHelper;
import com.example.shaurya.moviebuff.Model.MoviePOJO;
import com.example.shaurya.moviebuff.Parsers.MovieParser;
import com.example.shaurya.moviebuff.Tasks.GetMoviesTask;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements OnMovieClickListener {

    ViewPager viewPager;
    ViewPagerAdapter viewPagerAdapter;
    boolean twoPane = false;
    TabLayout slidingTab;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        isTwoPane();
        if (savedInstanceState != null) {
            initViewsWithoutViewPager();
            viewPager.setCurrentItem(savedInstanceState.getInt("viewpager_page", 0));
            int i = savedInstanceState.getInt("viewpager_page", 0);
            Log.d("currentpage", i+"");
        }
        else
            initViews();
        /*
        GetMoviesTask getMoviesTask = new GetMoviesTask(1, new OnTaskCompleted() {
            @Override
            public void onTaskStarted() {
                progressBar.setVisibility(View.VISIBLE);
            }

            @Override
            public void onTaskCompleted(String data) {
                MovieParser m = new MovieParser(data);
                ArrayList<MoviePOJO> mListPopular = m.getData();
                viewPagerAdapter.setmListPopular(mListPopular);
                ArrayList<MoviePOJO> mListTopRated = m.getData();
                viewPagerAdapter.setmListTopRated(mListTopRated);
                viewPagerAdapter.notifyDataSetChanged();
                progressBar.setVisibility(View.GONE);
            }
        });
        getMoviesTask.execute();
        */
    }

    private void isTwoPane() {
        FrameLayout frameLayout = (FrameLayout)findViewById(R.id.container_movie_detail);
        if (frameLayout != null)
            twoPane = true;
        SharedPreferences pref = getApplicationContext().getSharedPreferences(getString(R.string.sharedPreferenceName), 0); // 0 - for private mode
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean(getString(R.string.isTwoPane), twoPane);
        editor.commit();
    }

    private void initViewsWithoutViewPager() {
        viewPager = (ViewPager)findViewById(R.id.vpPager);
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.setmListSaved(MovieDBHelper.getInstance(MainActivity.this).getSavedMovies());
        viewPager.setAdapter(viewPagerAdapter);
        slidingTab = (TabLayout)findViewById(R.id.sliding_tabs);
        slidingTab.setupWithViewPager(viewPager);
    }

    private void initViews() {
        viewPager = (ViewPager)findViewById(R.id.vpPager);
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        //viewPagerAdapter.setmListSaved(MovieDBHelper.getInstance(MainActivity.this).getSavedMovies());
        viewPager.setAdapter(viewPagerAdapter);
        slidingTab = (TabLayout)findViewById(R.id.sliding_tabs);
        slidingTab.setupWithViewPager(viewPager);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        int i = viewPager.getCurrentItem();
        outState.putInt("viewpager_page",viewPager.getCurrentItem());
    }

    @Override
    public void onClick(MoviePOJO movieDetail) {
        if (twoPane){
            MovieDetailFragment movieDetailFragment = MovieDetailFragment.newInstance(movieDetail);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container_movie_detail, movieDetailFragment)
                    .commit();
        }
        else {
            Intent i = new Intent(MainActivity.this, MovieDetailActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("movie_detail", movieDetail);
            i.putExtras(bundle);
            startActivity(i);
        }
    }
}
