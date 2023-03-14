package com.example.mawsonmobileproject3;

import com.google.firebase.database.core.view.Event;

import java.util.List;
import java.util.Map;

public interface DocumentSnapshotInterface {
    void onCallback(List<Map<String,?>> myMoviesList);
}
