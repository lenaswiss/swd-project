package org.example.models;

import com.google.gson.annotations.SerializedName;

public class Movie {
    public String id;
    @SerializedName("rank")
    public String position;
    public String title;
    public String year;
    @SerializedName("imDbRating")
    public String rating;

    public Movie() {
    }

    public Movie(String title, String position, String year, String rating) {
        this.position = position;
        this.title = title;
        this.year = year;
        this.rating = rating;
    }

    public Movie(String title, String position, String year, String rating, String id) {
        this.id = id;
        this.position = position;
        this.title = title;
        this.year = year;
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "title='" + title + '\'' +
                ", position='" + position + '\'' +
                ", year='" + year + '\'' +
                ", rating='" + rating + '\'' +
                '}';
    }
}
