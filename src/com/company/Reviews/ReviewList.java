package com.company.Reviews;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ReviewList extends HashMap<Integer, Review> implements Serializable {

    public List<Integer> getCommonMovieIds(ReviewList comparisonReviewList) {
        List<Integer> movieIdsBothReviewed = new ArrayList<>();
        for (int movieId: keySet()) {
            if (comparisonReviewList.get(movieId) != null) {
                movieIdsBothReviewed.add(movieId);
            }
        }
        return movieIdsBothReviewed;
    }

    public double getMovieRating(int movieId) {
        try {
            return get(movieId).rating;
        } catch (NullPointerException ex) {
            return -1;
        }

    }

    public void addReview(int movieId, double rating) {
        put(movieId, new GeneratedReview(movieId, rating));
    }

    public double getAverageRating() {
        return values().stream().mapToDouble(review -> review.rating).average().getAsDouble();
    }

    public boolean isMovieReviewed(int movieId) {
        return get(movieId) != null;
    }


}