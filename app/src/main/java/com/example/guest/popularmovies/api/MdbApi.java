package com.example.guest.popularmovies.api;

import com.example.guest.popularmovies.BuildConfig;
import com.example.guest.popularmovies.mvp.model.MoviesArray;

import io.reactivex.Single;
import retrofit2.http.GET;

/**
 * Created by l1maginaire on 3/1/18.
 */

public interface MdbApi {
    @GET("3/movie/popular" + BuildConfig.API_KEY)
    Single<MoviesArray> getPopular();

    @GET("3/movie/top_rated" + BuildConfig.API_KEY)
    Single<MoviesArray> getTopRated();
}
