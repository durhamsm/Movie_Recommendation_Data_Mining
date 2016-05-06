package com.company.Users;

import com.company.Reviews.GeneratedReviewsList;
import com.company.Reviews.UserReviewsList;

import java.io.Serializable;

public class UserReviewData implements Serializable {

    public int id;
    public UserReviewsList userReviews = new UserReviewsList();
    public GeneratedReviewsList generatedReviews = new GeneratedReviewsList();

    public UserReviewData(int id) {
        this.id = id;
    }

    public double getAverageRating() {
        return userReviews.values().stream().mapToDouble(review -> review.rating).average().getAsDouble();
    }

    public void setRatingAsAverageOfOtherRatings(int movieId) {
        generatedReviews.addReview(movieId, getAverageRating());
    }

}