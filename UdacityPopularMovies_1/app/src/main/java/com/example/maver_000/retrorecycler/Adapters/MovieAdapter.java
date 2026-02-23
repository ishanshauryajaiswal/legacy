package com.example.maver_000.retrorecycler.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.maver_000.retrorecycler.Activities.MovieDetailActivity;
import com.example.maver_000.retrorecycler.Data.MoviePOJO;
import com.example.maver_000.retrorecycler.Helper.GetDate;
import com.example.maver_000.retrorecycler.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by maver_000 on 4/6/2016.
 */
public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    private List<MoviePOJO> mMovieList=new ArrayList<>();
    private LayoutInflater mInflater;
    private Context mContext;
    private String fragment_name;
    private GetDate release_date=new GetDate();

    public MovieAdapter(Context context,String fragment_name) {
        this.mContext = context;
        this.mInflater = LayoutInflater.from(context);
        this.fragment_name=fragment_name;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=mInflater.inflate(R.layout.grid_item,parent,false);
        final ViewHolder viewHolder=new ViewHolder(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position= viewHolder.getAdapterPosition();
                MoviePOJO temp=mMovieList.get(position);
                Intent intent=new Intent(mContext,MovieDetailActivity.class);
                intent.putExtra("movie_detail", temp);
                mContext.startActivity(intent);
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        MoviePOJO question=mMovieList.get(position);
        Picasso.with(mContext)
                .load(question.getPoster_path())
                .placeholder(R.color.colorAccent)
                .into(holder.imageView_poster);
        holder.textView_title.setText(question.getOriginal_title());
        if(fragment_name.equals(mContext.getString(R.string.fragment_popular)))
            holder.textView_info.setText(release_date.get_date(question.getRelease_date()));
        else
            holder.textView_info.setText(String.valueOf(question.getVote_average()));

    }

    @Override
    public int getItemCount() {

        return (mMovieList == null) ? 0 : mMovieList.size();
    }

    public void setMovieList(List<MoviePOJO> movieList) {
        if (!movieList.isEmpty()) {
            this.mMovieList.addAll(movieList);
            notifyDataSetChanged();
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        public TextView textView_title;
        public ImageView imageView_poster;
        public TextView textView_info;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView_poster=(ImageView)itemView.findViewById(R.id.imageView_poster);
            textView_title=(TextView)itemView.findViewById(R.id.title);
            textView_info=(TextView)itemView.findViewById(R.id.info);
        }
    }
}