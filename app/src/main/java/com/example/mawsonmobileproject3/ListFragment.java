package com.example.mawsonmobileproject3;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListFragment extends Fragment {

    OnItemSelectedListener clickListener;

    // protected MovieData md = new MovieData();

    // CHeck data in MovieData md
    Map md = new HashMap() {{
        put("index", 0);
        put("name", "BlockBuster");
        put("description", "This movie is a summer smash hit");
        put("year", "2022");
        put("length","1h 3m");
        put("rating","8");
        put("director","Yuen Woo Ping");
        put("stars","Harrison Forld");
        put("uri", "SOME URI");
        put("url", "SOME URL");
        put("selection",false);
    }};

    List<Map<String,?>> myList = new ArrayList<Map<String, ?>>() {{
        add(md);
        add(md);
    };
    };

    private static RecyclerViewAdapter recyclerViewAdapter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle b = new Bundle();

        //b.getSerializable("moviesList");
        //MovieObject Alice = (MovieObject) b.getSerializable("Alice");
        //System.out.println(Alice.toString());
         recyclerViewAdapter = new RecyclerViewAdapter(myList);

        Toast.makeText(getContext(), (String) myList.get(0).get("rating").toString(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance) {
        //Toast.makeText(getContext(), i, Toast.LENGTH_SHORT).show();
        //  Inflate the layout for this fragment
        // ArrayList retrieveMovie =savedInstance.getParcelableArrayList("moviesList");
        View rootView = inflater.inflate(R.layout.fragment_list, container, false);
        // Toast.makeText(getContext(), retrieveMovie.get(0).toString(), Toast.LENGTH_LONG).show();

        RecyclerView rv = rootView.findViewById(R.id.mainRecyclerView);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        layoutManager.scrollToPosition(0);
        rv.setLayoutManager(layoutManager);
        rv.setAdapter(recyclerViewAdapter);
        rv.setItemAnimator(new DefaultItemAnimator());
        return rootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            clickListener = (OnItemSelectedListener) context;
            recyclerViewAdapter.setOnItemSelectedListener(clickListener);
        } catch (ClassCastException ex) {
            throw new ClassCastException(context.toString() + "must implement EventTrack");
        }
    }

    public ListFragment() {
        // Required empty public constructor
    }
}