package com.example.mawsonmobileproject3;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> implements Filterable {
    private List<Map<String, ?>> md;  // List of all movies
    private List<Map<String, ?>> md_filtered;  // List of filtered movies
    private OnItemSelectedListener onListItemSelectedListener = null; // Call back to the Activity
    public RecyclerViewAdapter(List<Map<String, ?>> list) // List<Map<String, ?>> list)  // Constructor
    {
/*        HashMap movie = new HashMap();
        movie.put("index", Alice.index);
        movie.put("name", Alice.name);
        movie.put("description", Alice.description);
        movie.put("year", Alice.year);
        movie.put("length", Alice.length);
        movie.put("rating", Alice.rating);
        movie.put("director", Alice.director);
        movie.put("stars", Alice.stars);
        movie.put("uri", Alice.uri);
        movie.put("url", Alice.url);
        movie.put("selection",false); */

        //md_filtered.add(movie);
        //md_filtered.add(movie);
        md = md_filtered = list;
    }

    //OnItemClickListener vc=null;
    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView movie_name;
        public TextView movie_year;
        public ImageView poster_img;
        public TextView description;
        public ViewHolder(View view) {
            super(view);
            description = (TextView) view.findViewById(R.id.desc);
            movie_name = (TextView) view.findViewById(R.id.movie_name);
            movie_year = (TextView) view.findViewById(R.id.movie_year);
            poster_img = (ImageView) view.findViewById(R.id.poster_photo);
            poster_img.setScaleType(ImageView.ScaleType.FIT_START);
        }
    }

    public Map getItem(int i) {
        return md_filtered.get(i);
    }

    public void setOnItemSelectedListener(OnItemSelectedListener listener) {
        onListItemSelectedListener = (OnItemSelectedListener) listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_layout, parent, false);
        final ViewHolder view_holder = new ViewHolder(v);
        return view_holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        // int pos = holder.getAdapterPosition();
        holder.description.setText(md_filtered.get(position).get("description").toString());
        holder.movie_name.setText( md_filtered.get(position).get("name").toString());
        holder.movie_year.setText( md_filtered.get(position).get("year").toString());
        holder.poster_img.setImageResource(R.drawable.myface);//.parseInt(md_filtered.get(position).get("image").toString()));
        ViewCompat.setTransitionName(holder.poster_img, md_filtered.get(position).get("name").toString());
        holder.poster_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), "Inflating", Toast.LENGTH_SHORT).show();
                int position = holder.getBindingAdapterPosition();
                if(onListItemSelectedListener!=null)
                    onListItemSelectedListener.onListItemSelected(view, Integer.parseInt(md_filtered.get(position).get("image").toString()), md_filtered.get(position).get("name").toString(), md_filtered.get(position).get("year").toString());
            }
        });

    }

    /*
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_layout, parent, false);
        final ViewHolder view_holder = new ViewHolder(v);
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onListItemClickListener!=null)
                    onListItemClickListener.onItemClick(v,view_holder.getAdapterPosition());
            }
        });
        v.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (onListItemClickListener != null)
                    onListItemClickListener.onItemLongClick(v, view_holder.getAdapterPosition());
                return true;
            }
        });
        return view_holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.movie_name.setText( md_filtered.get(position).get("name").toString());
        holder.movie_year.setText( md_filtered.get(position).get("year").toString());
        holder.poster_img.setImageResource(Integer.parseInt(md_filtered.get(position).get("image").toString()));
    } */

    @Override
    public int getItemCount() { return md_filtered.size(); }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {

    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    md_filtered = md;
                } else {
                    List<Map<String,?>> filteredList = new ArrayList<>();
                    for (Map movie:md) {
                        //name match condition
                        if (movie.get("name").toString().toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(movie);
                        }
                    }
                    md_filtered = filteredList;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = md_filtered;
                return filterResults;
            }
            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                md_filtered = (ArrayList<Map<String, ?>>) filterResults.values;
                notifyDataSetChanged();
            }

        };
    }
}
