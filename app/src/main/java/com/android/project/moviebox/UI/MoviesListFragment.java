package com.android.project.moviebox.UI;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.android.project.moviebox.Adapter.CardsViewAdapter;
import com.android.project.moviebox.BaseClass.BaseFragment;
import com.android.project.moviebox.Interfaces.MoviesFragmentView;
import com.android.project.moviebox.DataModels.MoviesFragmentViewModel;
import com.android.project.moviebox.R;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

public class MoviesListFragment extends BaseFragment<MoviesFragmentViewModel> implements MoviesFragmentView {

    private RecyclerView mCardsListView;
    private TextView mDefaultMessage;
    private Button mRefreshButtonView;
    private Button mFilterView;

    private List<JsonObject> cardObjects = new ArrayList<>();

    private CardsViewAdapter mAdpater;

    public MoviesListFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.cards_layout, container, false);

        fragmentViewModel = new MoviesFragmentViewModel(getContext());
        fragmentViewModel.attach(this);
        mRefreshButtonView = rootView.findViewById(R.id.refresh_view);
        mRefreshButtonView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentViewModel.fetchListFromServer();
            }
        });
        mFilterView = rootView.findViewById(R.id.filter_view);
        mFilterView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentViewModel.filterMoviesListByData(cardObjects);
            }
        });

        mAdpater = new CardsViewAdapter(getContext(), cardObjects);
        mCardsListView = rootView.findViewById(R.id.listView);

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(mCardsListView.getContext(),
                mLayoutManager.getOrientation());

        mCardsListView.setItemAnimator(new DefaultItemAnimator());
        mCardsListView.setLayoutManager(mLayoutManager);
        mCardsListView.addItemDecoration(dividerItemDecoration);
        mCardsListView.setAdapter(mAdpater);

        mDefaultMessage = rootView.findViewById(R.id.default_message);

        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        showProgress(getString(R.string.info_fetching_more_cards_message));
        // Get the list as soon as user open the application.
        fragmentViewModel.fetchListFromServer();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void updateAdapter(List<JsonObject> result) {
        if (result != null && result.size() > 0) {
            stopProgress();
            cardObjects.addAll(result);
            mDefaultMessage.setVisibility(View.GONE);
            mAdpater.notifyDataSetChanged();
        }
    }

    @Override
    public void updateSortedList(List<JsonObject> result) {
        if (result != null && result.size() > 0) {
            cardObjects = result;
            mAdpater.notifyDataSetChanged();
        }
    }

    @Override
    public void error(Throwable e) {

    }

    @Override
    public void error(String msg) {
        stopProgress();
        mDefaultMessage.setVisibility(View.VISIBLE);
        mDefaultMessage.setText(msg);
    }

}
