package com.android.project.moviebox.DataModels;

import android.content.Context;
import android.util.Log;

import com.android.project.moviebox.BaseClass.BaseViewModel;
import com.android.project.moviebox.Interfaces.MoviesFragmentView;
import com.android.project.moviebox.Network.RetrofitService;
import com.android.project.moviebox.R;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.observers.ResourceObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Class related to operations w.r.t MoviesFragmentViewModel
 */
public class MoviesFragmentViewModel extends BaseViewModel<MoviesFragmentView> {


    private static String TAG = "MoviesFragmentViewModel";

    private Context mContext;

    public MoviesFragmentViewModel(Context context) {
        mContext = context;
    }

    /**
     * Fetch Movies from the server
     */
    public void fetchListFromServer() {
        compositeDisposable.add(RetrofitService.getInstance().getMoviesList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(getMoviesListObserver()));
    }

    private DisposableSingleObserver<JsonArray> getMoviesListObserver() {
        return new DisposableSingleObserver<JsonArray>() {
            @Override
            public void onSuccess(JsonArray value) {
                processServerResponse(value);
            }

            @Override
            public void onError(Throwable e) {
                view.error(mContext.getString(R.string.info_no_network_message));
            }
        };
    }

    void processServerResponse(JsonArray jsonArrayResponse) {
        Observable.just(jsonArrayResponse)
                .subscribeOn(Schedulers.computation())//creation of secondary thread
                .map(new Function<JsonArray, List<JsonObject>>() {//<input obj,return obj>
                    @Override
                    public List<JsonObject> apply(JsonArray jsonArray) {//input obj
                        List<JsonObject> serverListObj = new ArrayList<JsonObject>();

                        if (jsonArray != null) {
                            for (int i = 0; i < jsonArray.size(); i++) {
                                JsonObject tvListObj = jsonArray.get(i).getAsJsonObject();
                                serverListObj.add(tvListObj);
                            }
                        }
                        //runs in a secondary thread
                        return serverListObj;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new ResourceObserver<List<JsonObject>>() {
                    @Override
                    public void onNext(List<JsonObject> value) {
                        Log.d(TAG, "on next");
                        view.updateAdapter(value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "error ");
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "onCompleted ");
                    }
                });//now this runs in main thread
    }

    /**
     * Filter movies from the list based on
     * 1. Date
     * 2. Alphabetical order
     * 3. Popularity
     * 4. Genres
     */
    public void filterMoviesListByData(List<JsonObject> cardObjects) {
        Observable.just(cardObjects)
                .subscribeOn(Schedulers.computation())//creation of secondary thread
                .map(new Function<List<JsonObject>, List<JsonObject>>() {//<input obj,return obj>
                    @Override
                    public List<JsonObject> apply(List<JsonObject> moviesList) {//input obj
                        Collections.sort(moviesList, new Comparator<JsonObject>() {
                            @Override
                            public int compare(JsonObject card1, JsonObject card2) {
                                /* For ascending order */
                                return card1.get("id").getAsInt() - card2.get("id").getAsInt();
                            }
                        });

                        //runs in a secondary thread
                        return moviesList;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new ResourceObserver<List<JsonObject>>() {
                    @Override
                    public void onNext(List<JsonObject> value) {
                        Log.d(TAG, "on next");
                        view.updateSortedList(value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "error ");
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "onCompleted ");
                    }
                });//now this runs in main thread
    }

}
