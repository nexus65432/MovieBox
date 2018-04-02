package com.android.project.moviebox.Interfaces;


import com.google.gson.JsonObject;

import java.util.List;

public interface FetchTaskListener {

    void onTaskStarted();

    void onTaskFinished(List<JsonObject> jsonObjectList);
}
