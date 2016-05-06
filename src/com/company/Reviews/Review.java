package com.company.Reviews;

import java.io.Serializable;

public class Review implements Serializable {

    public int movieId;
    public double rating;

    public Review(int movieId, double rating) {
        this.movieId = movieId;
        this.rating = rating;
    }

}