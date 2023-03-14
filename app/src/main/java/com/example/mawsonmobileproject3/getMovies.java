package com.example.mawsonmobileproject3;
/*
import static android.content.ContentValues.TAG;

import static com.firebase.ui.auth.AuthUI.getApplicationContext;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Map;

public class getMovies extends Application {
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private MovieData md=new MovieData();

    public void populateMovieList() {
        db.collection("Movies")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        String avatarStr = null;
                        int index = 0;
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Map<String, Object> movieMap = document.getData();// ("Avatar").toString();
                                md.createMovie(index, (String) document.getId(), (String) movieMap.get("image"), (String) movieMap.get("description"), (String) movieMap.get("year"),
                                        (String) movieMap.get("length"), (Double) movieMap.get("rating"), (String) movieMap.get("director"), (String) movieMap.get("stars"), (String) movieMap.get("url"));
                                ++index;
                                // Log.d(TAG, document.getId() + " => " + document.getData());
                            }
                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                        //Toast.makeText(this, md.getItem(4).get("name").toString(), Toast.LENGTH_LONG).show();
                    }

                });
    }

    public MovieData getMovieData() {
        return md;
    }
}
*/