package com.example.shaurya.moviebuff;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.shaurya.moviebuff.Database.MovieDBHelper;
import com.example.shaurya.moviebuff.Model.MoviePOJO;
import com.example.shaurya.moviebuff.Parsers.MovieParser;
import com.example.shaurya.moviebuff.Tasks.GetMoviesTask;

import java.util.ArrayList;

/**
 * Created by shaurya on 10/02/18.
 */

public class PopularMovieFragment extends Fragment {

    RecyclerView recyclerView;
    ArrayList<MoviePOJO> mList;
    RecyclerViewAdapter rvAdapter;
    OnMovieClickListener mListener;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return (View)inflater.inflate(R.layout.fragment_main,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mList = new ArrayList<>();
        recyclerView = (RecyclerView)view.findViewById(R.id.rv_Movie);
        Bundle arguments = getArguments();
        if (arguments != null && arguments.getInt("FragmentForFavourites")==3){
            mList = MovieDBHelper.getInstance(getActivity()).getSavedMovies();
            if (mList.size()>0){
                rvAdapter = new RecyclerViewAdapter(mList, getActivity(), mListener);
                recyclerView.setAdapter(rvAdapter);
                GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
                recyclerView.setLayoutManager(gridLayoutManager);
            }
            else {
                Toast.makeText(getActivity(), "No Favourites Selected", Toast.LENGTH_SHORT).show();
            }
        }

        //mList = (ArrayList<MoviePOJO>) getArguments().getSerializable("mList");

        else{
            rvAdapter = new RecyclerViewAdapter(getActivity(), mListener);
            recyclerView.setAdapter(rvAdapter);
            GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
            recyclerView.setLayoutManager(gridLayoutManager);
            loadMovies(1);
            recyclerView.addOnScrollListener(new EndlessRecyclerViewScrollListener(gridLayoutManager) {
                @Override
                public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                    loadMovies(page);
                }
            });
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (getActivity() instanceof OnMovieClickListener)
            mListener = (OnMovieClickListener) getActivity();
        else
            throw new ClassCastException(getActivity().toString() + " must implement OnMovieClickListener");
    }

    public void loadMovies(int page){
        GetMoviesTask getMoviesTask = new GetMoviesTask(page, new OnTaskCompleted() {
            @Override
            public void onTaskStarted() {
            }

            @Override
            public void onTaskCompleted(String data) {
                MovieParser m = new MovieParser(data);
                ArrayList<MoviePOJO> list = m.getData();
                mList.addAll(list);
                rvAdapter.setmList(mList);
                rvAdapter.notifyDataSetChanged();
            }
        });
        Bundle arguments = getArguments();
        if (arguments != null && arguments.getInt("FragmentForFavourites")==1)
            getMoviesTask.execute("http://api.themoviedb.org/3/movie/popular?api_key=ab5aee0b18da89dd9e026d35754c24f1&page=");
        else if (arguments != null && arguments.getInt("FragmentForFavourites")==2)
            getMoviesTask.execute("http://api.themoviedb.org/3/movie/top_rated?api_key=ab5aee0b18da89dd9e026d35754c24f1&page=");
    }

    public static PopularMovieFragment newInstance(){
        PopularMovieFragment movieFragment = new PopularMovieFragment();
        //Bundle bundle = new Bundle();
        //bundle.putSerializable("mList",mList);
        //movieFragment.setArguments(bundle);
        return movieFragment;
    }

    public static PopularMovieFragment newInstance(int favouriteList){
        PopularMovieFragment movieFragment = new PopularMovieFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("FragmentForFavourites", favouriteList);
        movieFragment.setArguments(bundle);
        return movieFragment;
    }
}
