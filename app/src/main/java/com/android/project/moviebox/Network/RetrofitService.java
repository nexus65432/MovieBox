package com.android.project.moviebox.Network;

import com.android.project.moviebox.Constants.AppConstants;
import com.android.project.moviebox.Interfaces.FetchMoviesInterface;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import io.reactivex.Single;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class RetrofitService {

    private static RetrofitService instance;
    private FetchMoviesInterface mFetchMoviesInterface;

    private RetrofitService() {
        final Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();
        final Retrofit retrofit = new Retrofit.Builder().baseUrl(AppConstants.END_POINT)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        mFetchMoviesInterface = retrofit.create(FetchMoviesInterface.class);
    }

    public static RetrofitService getInstance() {
        if (instance == null) {
            instance = new RetrofitService();
        }
        return instance;
    }

    public Single<JsonArray> getMoviesList() {
        return mFetchMoviesInterface.fetchMoviesList();
    }
}
