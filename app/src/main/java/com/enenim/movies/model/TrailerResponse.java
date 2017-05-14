package com.enenim.movies.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by enenim on 5/13/17.
 */

public class TrailerResponse {
    @SerializedName("id")
    private Long id;

    @SerializedName("results")
    private List<Trailer> results;

    public List<Trailer> getResults() {
        return results;
    }

    public void setResults(List<Trailer> results) {
        this.results = results;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
