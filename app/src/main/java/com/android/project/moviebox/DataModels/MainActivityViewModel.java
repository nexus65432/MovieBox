package com.android.project.moviebox.DataModels;

import com.android.project.moviebox.BaseClass.BaseViewModel;
import com.android.project.moviebox.Interfaces.MainActivityView;

/**
 * Created on 3/23/18.
 */
public class MainActivityViewModel extends BaseViewModel<MainActivityView> {

    public MainActivityViewModel() {
    }

    public void addMoviesList() {
        view.showMoviesList();
    }

}
