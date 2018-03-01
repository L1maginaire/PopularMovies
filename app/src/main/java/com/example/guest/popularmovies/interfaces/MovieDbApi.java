package com.example.guest.popularmovies.interfaces;

/**
 * Created by guest on 2/20/18.
 */

import com.example.guest.popularmovies.BuildConfig;
import com.example.guest.popularmovies.mvp.model.MoviesArray;

import retrofit2.http.GET;
import io.reactivex.Single;

public interface MovieDbApi {
    static final String API_KEY = BuildConfig.API_KEY;

    @GET("3/movie/top_rated?api_key=f9a771d5870087b32be1a05bcb8ef697")
    Single<MoviesArray> getDefault(/*@Query("api_key") int limit, @Query("api-key") String key, @Query("offset") int offset*/);
}