package org.example.models;

import com.google.gson.annotations.SerializedName;

public class Ratings {
    @SerializedName("imDbId")
    public String id;
    public String title;
    @SerializedName("imDb")
    public String rating;

    @Override
    public String toString() {
        return "Ratings{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", rating='" + rating + '\'' +
                '}';
    }
}
