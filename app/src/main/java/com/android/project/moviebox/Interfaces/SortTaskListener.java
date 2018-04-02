package com.android.project.moviebox.Interfaces;

import com.google.gson.JsonObject;

import java.util.List;

public interface SortTaskListener {

    void onSortStarted();

    void onSortFinished(List<JsonObject> jsonObjectList);
}
