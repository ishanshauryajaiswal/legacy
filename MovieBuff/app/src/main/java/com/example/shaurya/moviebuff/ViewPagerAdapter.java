package com.example.shaurya.moviebuff;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.shaurya.moviebuff.Database.MovieDBHelper;
import com.example.shaurya.moviebuff.Model.MoviePOJO;

import java.util.ArrayList;

/**
 * Created by shaurya on 10/02/18.
 */

public class ViewPagerAdapter extends FragmentPagerAdapter {
    ArrayList<MoviePOJO> mListPopular, mListTopRated, mListSaved;

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                    return PopularMovieFragment.newInstance(1);
            case 1:
                    return PopularMovieFragment.newInstance(2);
            case 2:
                    return PopularMovieFragment.newInstance(3);
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
            return 3;
        /*
        if (mListPopular!=null && mListSaved.size()>0)
            return 3;
        if (!(mListSaved.size()>0) && mListPopular!=null)
            return 2;
        return 0;
        */
    }

    @Override
    public CharSequence getPageTitle(int position)
    {
        switch (position){
            case 0:
                return "Popular";
            case 1:
                return "Top Rated";
            case 2:
                return "Favourite";
            default:
                return null;
        }
    }

    public void setmListTopRated(ArrayList<MoviePOJO> mListTopRated) {
        this.mListTopRated = mListTopRated;
    }

    public void setmListPopular(ArrayList<MoviePOJO> mListPopular) {
        this.mListPopular = mListPopular;
    }

    public void setmListSaved(ArrayList<MoviePOJO> mListSaved) {
        this.mListSaved = mListSaved;
    }
}
