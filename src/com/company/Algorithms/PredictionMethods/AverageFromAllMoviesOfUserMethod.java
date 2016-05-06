package com.company.Algorithms.PredictionMethods;

import com.company.Reviews.GeneratedReviewsList;
import com.company.Users.UserReviewData;
import com.company.Users.UserReviewDataSet;

public class AverageFromAllMoviesOfUserMethod extends RatingPredictionMethod {


    public AverageFromAllMoviesOfUserMethod() {

    }

    @Override
    public GeneratedReviewsList fill(UserReviewData userReviewData, UserReviewDataSet userReviewDataSet, int movieId) throws Exception {
        setValues(userReviewData, userReviewDataSet, movieId);
        addAverageFromAllMoviesOfUser(userReviewData, movieId);
        return userReviewData.generatedReviews;
    }

    private void addAverageFromAllMoviesOfUser(UserReviewData userReviewDataToFillIn, int movieId) {
        userReviewDataToFillIn.generatedReviews.addReview(movieId, userReviewDataToFillIn.userReviews.getAverageRating());
    }
}
