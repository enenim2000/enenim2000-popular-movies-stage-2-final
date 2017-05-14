package com.enenim.movies.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by enenim on 4/29/17.
 */


public class ReviewResponse {
    @SerializedName("id")
    private Long id;

    @SerializedName("results")
    private List<Review> results;

    public List<Review> getResults() {
        return results;
    }

    public void setResults(List<Review> results) {
        this.results = results;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}