package com.example.mawsonmobileproject3;

import static android.content.ContentValues.TAG;
import static android.os.SystemClock.sleep;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;
import androidx.transition.Fade;
import androidx.transition.Slide;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.ListResult;
import com.google.firebase.storage.StorageReference;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MoviesActivity extends AppCompatActivity implements OnItemSelectedListener {
    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;
    FirebaseStorage storage = FirebaseStorage.getInstance();
    public static List<Map<String, ?>> moviesList = new ArrayList<Map<String, ?>>();
    public static MovieData md = new MovieData();
    private boolean twoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.activity_movies);
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        populateMovieDatabase(db, new DocumentSnapshotInterface() {
            @Override
            public void onCallback(List<Map<String, ?>> myMoviesList) {
                setContentView(R.layout.activity_recycler);
                // ArrayList myArrayList = new ArrayList(myMoviesList);
                System.out.println(myMoviesList.get(0)+" "+myMoviesList.get(1)+" "+myMoviesList.get(2));
                Bundle bundle = new Bundle();
                // System.out.println(String.valueOf(movies.get(5).get("director")));

                // MovieObject Alice = new MovieObject((int) myMoviesList.get(0).get("index"),(String) myMoviesList.get(0).get("name"), (String) myMoviesList.get(0).get("uri"), (String) myMoviesList.get(0).get("description"), (String) myMoviesList.get(0).get("year"), (String) myMoviesList.get(0).get("length"), (double) myMoviesList.get(0).get("rating"), (String) myMoviesList.get(0).get("director"), (String) myMoviesList.get(0).get("stars"), (String) myMoviesList.get(0).get("url"));
                //bundle.putSerializable("Alice", Alice);
                com.example.mawsonmobileproject3.ListFragment movieFrag = new com.example.mawsonmobileproject3.ListFragment();
                movieFrag.setArguments(bundle);
//                Toast.makeText(getApplicationContext(), myArrayList.get(0).toString(), Toast.LENGTH_LONG).show();
                getSupportFragmentManager().beginTransaction().replace(R.id.main_container, movieFrag, null).commit();
            }
        });
        // Toast.makeText(getApplicationContext(), moviesList.get(0).get("stars").toString(), Toast.LENGTH_LONG).show();

        // Toast.makeText(this, myList.get(0).get("name").toString(), Toast.LENGTH_LONG).show();
        //
        //Toast.makeText(this, (String) md.getItem(4).get("description").toString(), Toast.LENGTH_LONG).show();

//        setContentView(R.layout.activity_recycler);
//        if (savedInstanceState == null) {
//            //getSupportFragmentManager().beginTransaction().replace(R.id.main_container, new com.example.mawsonmobileproject3.ListFragment(), null).commit();
//        }
//        twoPane = false;
//        if (findViewById(R.id.detail_container) != null) {
//            twoPane = true;
//        }
    }

    public boolean onListItemSelected(View sharedView, int imageResourceID, String title, String year) {
        Bundle args = new Bundle();
        args.putInt("img_id", imageResourceID);
        args.putString("mtitle", title);
        args.putString("myear", year);
        Fragment detailFragment = new DetailFragment();
        detailFragment.setArguments(args);
        if (twoPane) {
            detailFragment.setEnterTransition(new Slide(Gravity.TOP));
            detailFragment.setExitTransition(new Slide(Gravity.BOTTOM));
            getSupportFragmentManager().beginTransaction().replace(R.id.detail_container, detailFragment).addToBackStack(null).commit();
        } else {
            detailFragment.setEnterTransition(new com.example.mawsonmobileproject3.DetailsTransition());
            detailFragment.setEnterTransition(new Fade().setDuration(200));
            detailFragment.setReturnTransition(new com.example.mawsonmobileproject3.DetailsTransition());
            detailFragment.setExitTransition(new Fade().setDuration(200));
            getSupportFragmentManager().beginTransaction().addSharedElement(sharedView, ViewCompat.getTransitionName(sharedView)).replace(R.id.main_container, detailFragment).addToBackStack(null).commit();
        }
        return true;
    }


    public void populateMovieDatabase(FirebaseFirestore db, DocumentSnapshotInterface myCallBack) {
        db.collection("Movies")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        int index = 0;
                        if (task.isSuccessful()) {
                            List<Map<String, ?>> moviesList = new ArrayList<Map<String, ?>>();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Map<String, Object> movieMap = document.getData();// ("Avatar").toString();
                                //Toast.makeText(getApplicationContext(), movieMap.get("director").toString(), Toast.LENGTH_LONG).show();
                                moviesList.add(index, md.createMovie(index, (String) document.getId().toString(), (String) movieMap.get("image").toString(), (String) movieMap.get("description").toString(), (String) movieMap.get("year").toString(),
                                        (String) movieMap.get("length").toString(), (Double) movieMap.get("rating"), (String) movieMap.get("director").toString(), (String) movieMap.get("stars").toString(), (String) movieMap.get("url").toString()));
                                ++index;
                                // Toast.makeText(getApplicationContext(), moviesList.get(0).get("name").toString(), Toast.LENGTH_LONG).show();
                                // Log.d(TAG, document.getId() + " => " + document.getData());
                            }
                            myCallBack.onCallback(moviesList);
                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });
    }
}

/*
        StorageReference gsReference = storage.getReferenceFromUrl("gs://mobile-app-lab3.appspot.com/alice.jpg");
        // Reference to an image file in Cloud Storage
        // gs://mobile-app-lab3.appspot.com/alice.jpg
           StorageReference storageReference = FirebaseStorage.getInstance("gs://mobile-app-lab3.appspot.com/").getReference("alice.jpg");

        final long ONE_MEGABYTE = 1024 * 1024;
        gsReference.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
            @Override
            public void onSuccess(byte[] bytes) {

                // ImageView in your Activity
                ImageView imageView = findViewById(R.id.HomeScreenImage);
                Bitmap myBitmap = BitmapFactory.decodeByteArray(bytes,0,bytes.length);
                imageView.setImageBitmap(myBitmap);
                // Data for "images/island.jpg" is returns, use this as needed
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle any errors
            }
        });
    }
*/
