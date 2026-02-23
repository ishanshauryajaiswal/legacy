package com.example.shaurya.moviebuff;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shaurya.moviebuff.Database.MovieDBHelper;
import com.example.shaurya.moviebuff.Model.MoviePOJO;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by shaurya on 10/02/18.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<MoviePOJO> mList;
    private Context mContext;
    OnMovieClickListener mListener;

    public RecyclerViewAdapter(Context mContext, OnMovieClickListener mListener){
        this.mList = new ArrayList<>();
        this.mContext = mContext;
        this.mListener = mListener;
    }

    public RecyclerViewAdapter(ArrayList<MoviePOJO> mList, Context mContext, OnMovieClickListener mListener){
        this.mList = mList;
        this.mContext = mContext;
        this.mListener = mListener;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.recycler_view_cell,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final MoviePOJO moviePOJO = mList.get(position);
        ViewHolder viewHolder = (ViewHolder)holder;
        viewHolder.textViewTitle.setText(moviePOJO.getTitle());
        viewHolder.textViewRating.setText(moviePOJO.getVote_average()+"");
        Picasso.with(mContext)
                .load(moviePOJO.getPoster_path())
                .placeholder(R.color.colorAccent)
                .into(viewHolder.imageView);
        viewHolder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onClick(moviePOJO);
            }
        });
        SharedPreferences pref = mContext.getSharedPreferences(mContext.getString(R.string.sharedPreferenceName), 0);
        if (position == 0 && pref.getBoolean(mContext.getString(R.string.isTwoPane), false)) {
            mListener.onClick(moviePOJO);
            //MovieDBHelper m = MovieDBHelper.getInstance(mContext);
            //m.addMovie(moviePOJO);
            //m.getSavedMovies();
        }
    }

    @Override
    public int getItemCount() {
        return (mList == null) ? 0 : mList.size();
    }

    public void setmList(ArrayList<MoviePOJO> mList) {
        this.mList = mList;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView textViewTitle, textViewRating;
        public ViewHolder(View view){
            super(view);
            imageView = (ImageView) view.findViewById(R.id.imgView_movie);
            textViewRating = (TextView) view.findViewById(R.id.txtView_movieRating);
            textViewTitle = (TextView) view.findViewById(R.id.txtView_movieName);
        }
    }
}
