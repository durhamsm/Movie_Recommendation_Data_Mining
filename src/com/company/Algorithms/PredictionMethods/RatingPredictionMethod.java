package com.company.Algorithms.PredictionMethods;

import com.company.Reviews.GeneratedReviewsList;
import com.company.Users.UserReviewData;
import com.company.Users.UserReviewDataSet;

public abstract class RatingPredictionMethod {

    public UserReviewData userReviewData;
    public UserReviewDataSet userReviewDataSet;
    public int movieId;



    private int count = 0;

    public abstract GeneratedReviewsList fill(UserReviewData userReviewData, UserReviewDataSet userReviewDataSet, int movieId) throws Exception;

    public RatingPredictionMethod() {

    }

    public void setValues(UserReviewData userReviewData, UserReviewDataSet userReviewDataSet, int movieId) {
        this.userReviewData = userReviewData;
        this.userReviewDataSet = userReviewDataSet;
        this.movieId = movieId;
    }

    public void iterateCount() {
        ++count;
    }

}
