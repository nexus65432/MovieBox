package com.android.project.moviebox.UI;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;

import com.android.project.moviebox.BaseClass.BaseActivity;
import com.android.project.moviebox.Interfaces.MainActivityView;
import com.android.project.moviebox.DataModels.MainActivityViewModel;
import com.android.project.moviebox.R;

public class MainActivity extends BaseActivity<MainActivityViewModel> implements MainActivityView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new MainActivityViewModel();
        viewModel.attach(this);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();
        viewModel.addMoviesList();
    }

    @Override
    public void error(Throwable e) {

    }

    @Override
    public void error(String msg) {

    }

    @Override
    public void showMoviesList() {
        MoviesListFragment moviesFragment = new MoviesListFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, moviesFragment);
        transaction.commit();
    }

}
