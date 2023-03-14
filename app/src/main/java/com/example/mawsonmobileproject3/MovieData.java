package com.example.mawsonmobileproject3;

import static android.content.ContentValues.TAG;

import static com.firebase.ui.auth.AuthUI.getApplicationContext;

import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MovieData {
    public List<Map<String,?>> moviesList;
    public List<Map<String, ?>> getMoviesList() {
        return moviesList;
    }
    public int getSize(){
        return moviesList.size();
    }

    public HashMap getItem(int i){
        if (i >=0 && i < moviesList.size()){
            return (HashMap) moviesList.get(i);
        } else return null;
    }

    public MovieData(){
        String index;
        String name;
        String description;
        String length;
        String year;
        double rating;
        String director;
        String stars;
        String url;
        String uri;
        moviesList = new ArrayList<Map<String,?>>();
    }

    public HashMap createMovie(int index, String name, String gsUri, String description, String year,
                                String length, double rating, String director, String stars, String url) {
        HashMap movie = new HashMap();
        movie.put("index", index);
        movie.put("name", name);
        movie.put("description", description);
        movie.put("year", year);
        movie.put("length",length);
        movie.put("rating",rating);
        movie.put("director",director);
        movie.put("stars",stars);
        movie.put("uri", gsUri);
        movie.put("url",url);
        movie.put("selection",false);
        moviesList.add(index, movie);
        return movie;
    }

    public String getDirName() {
        return (String) moviesList.get(5).get("director").toString();
    }

    public void addMovie(int index, String name, int image, String year){
        //moviesList.add(index, createMovie(name, image, "description", year, "length", 10, "director", "stars", "url"));
    }
}
