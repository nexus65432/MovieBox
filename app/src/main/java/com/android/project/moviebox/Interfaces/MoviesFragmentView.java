package com.android.project.moviebox.Interfaces;

import com.google.gson.JsonObject;

import java.util.List;

/**
 * Interface related to MoviesView
 * Updating adapter
 * sorting movies list
 * Reordering
 * ...
 */
public interface MoviesFragmentView extends IView {

    void updateAdapter(List<JsonObject> result);

    void updateSortedList(List<JsonObject> result);
}
