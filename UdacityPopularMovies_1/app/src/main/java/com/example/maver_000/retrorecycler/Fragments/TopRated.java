package com.example.maver_000.retrorecycler.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.maver_000.retrorecycler.Adapters.MovieAdapter;
import com.example.maver_000.retrorecycler.Data.MoviePOJO;
import com.example.maver_000.retrorecycler.Helper.EndlessRecyclerViewScrollListener;
import com.example.maver_000.retrorecycler.R;
import com.example.maver_000.retrorecycler.Rest.MovieResponse;
import com.example.maver_000.retrorecycler.Rest.MovieAPI;

import java.util.ArrayList;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by Ishan Shaurya Jaiswal.
 */
public class TopRated extends Fragment implements Callback<MovieResponse>{

    RecyclerView recyclerView;
    MovieAdapter movieAdapter;
    private ArrayList<MoviePOJO> mlist;
    private ArrayList<MoviePOJO>mlist_save_state=new ArrayList<>();
    ProgressBar progressBar;
    public TopRated() {
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState!=null)
           mlist_save_state=savedInstanceState.getParcelableArrayList(getString(R.string.saved_list));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.activity_main,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView=(RecyclerView)view.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        movieAdapter=new MovieAdapter(getActivity(),getString(R.string.fragment_top_rated));
        progressBar= (ProgressBar) view.findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);
        recyclerView.setAdapter(movieAdapter);
        GridLayoutManager grid_layout_manager=new GridLayoutManager(getContext(),2);
        recyclerView.setLayoutManager(grid_layout_manager);
        if (savedInstanceState!=null){
            progressBar.setVisibility(View.GONE);
            mlist_save_state=savedInstanceState.getParcelableArrayList(getString(R.string.saved_list));
            movieAdapter.setMovieList(mlist_save_state);
        }
        else {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(getString(R.string.base_url))
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            MovieAPI stackOverflowAPI = retrofit.create(MovieAPI.class);
            Call<MovieResponse> call = stackOverflowAPI.load_top(getString(R.string.tmdb_api_key), 1);
            call.enqueue(this);
        }
        recyclerView.addOnScrollListener(new EndlessRecyclerViewScrollListener(grid_layout_manager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount) {
                progressBar.setVisibility(View.VISIBLE);
                getmore(page);

            }
        });

    }

    public void getmore(int offset){
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(getString(R.string.base_url))
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        MovieAPI stackOverflowAPI = retrofit.create(MovieAPI.class);
        Call<MovieResponse> call = stackOverflowAPI.load_top(getString(R.string.tmdb_api_key),offset);
        call.enqueue(this);

    }
    @Override
    public void onResponse(Response<MovieResponse> response, Retrofit retrofit) {
        progressBar.setVisibility(View.GONE);
        mlist= (ArrayList<MoviePOJO>) response.body().results;
        mlist_save_state.addAll(mlist);
        movieAdapter.setMovieList(mlist);
    }
    @Override
    public void onFailure(Throwable t) {
        if (mlist==null) {
            progressBar.setVisibility(View.GONE);
            Toast.makeText(getContext(), R.string.toast_check_network, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(getString(R.string.saved_list),mlist_save_state);
    }
}
