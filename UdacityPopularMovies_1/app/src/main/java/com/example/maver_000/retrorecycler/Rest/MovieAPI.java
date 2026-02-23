package com.example.maver_000.retrorecycler.Rest;

import com.example.maver_000.retrorecycler.Rest.MovieResponse;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by maver_000 on 4/5/2016.
 */
public interface MovieAPI {
    @GET("/3/movie/popular")
    Call<MovieResponse> load_popular(@Query("api_key")String apiKey,
                                     @Query("page")int page);
    @GET("/3/movie/top_rated")
    Call<MovieResponse> load_top(@Query("api_key")String apiKey,
                                 @Query("page")int page);
}
