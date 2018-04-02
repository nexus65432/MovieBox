package com.android.project.moviebox.Interfaces;

import com.android.project.moviebox.Constants.AppConstants;
import com.google.gson.JsonArray;

import io.reactivex.Single;
import retrofit2.http.GET;

public interface FetchMoviesInterface {

    @GET(AppConstants.POST_URL)
    Single<JsonArray> fetchMoviesList();
}
