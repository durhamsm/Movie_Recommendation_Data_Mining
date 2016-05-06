package com.company.Algorithms.PredictionMethods;

import com.company.Reviews.GeneratedReviewsList;
import com.company.Users.UserReviewData;
import com.company.Users.UserReviewDataSet;

import java.util.NoSuchElementException;

public class AverageAllUsersForMovieMethod extends RatingPredictionMethod {

    public AverageAllUsersForMovieMethod() {

    }

    @Override
    public GeneratedReviewsList fill(UserReviewData userReviewData, UserReviewDataSet userReviewDataSet, int movieId) throws Exception {
        setValues(userReviewData, userReviewDataSet, movieId);
        userReviewData.generatedReviews.addReview(movieId, getAverageRating(movieId));
        return userReviewData.generatedReviews;
    }

    private double getAverageRating(int movieId) throws NoSuchElementException {
        return userReviewDataSet.values().stream().filter(user -> user.userReviews.isMovieReviewed(movieId))
                .mapToDouble(user -> user.userReviews.getMovieRating(movieId)).average().getAsDouble();
    }
}
