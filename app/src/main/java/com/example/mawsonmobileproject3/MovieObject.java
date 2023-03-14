package com.example.mawsonmobileproject3;

import static java.lang.Integer.valueOf;

import android.os.Parcelable;

import java.io.Serializable;

public class MovieObject implements Serializable {

    int index;
    String name;
    String description;
    String length;
    String year;
    double rating;
    String director;
    String stars;
    String url;
    String uri;

    public int getIndex() {
        return index;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getLength() {
        return length;
    }

    public String getYear() {
        return year;
    }

    public double getRating() {
        return rating;
    }

    public String getDirector() {
        return director;
    }

    public String getStars() {
        return stars;
    }

    public String getUrl() {
        return url;
    }

    public String getUri() {
        return uri;
    }

    public MovieObject(int myindex, String myname, String mygsUri, String mydescription, String myyear,
                       String mylength, double myrating, String mydirector, String mystars, String myurl) {
        index = myindex;
        name = myname;
        description = mydescription;
        length = mylength;
        year = myyear;
        rating = myrating;
        director = mydirector;
        stars = mystars;
        url = myurl;
        uri = mygsUri;
    }

    @Override
    public String toString() {
        return valueOf(index)+" "+name+" "+description+" "+length+" "+year+" "+String.valueOf(rating)+" "+director+" "+stars+" "+url+" "+uri;
    }
}
