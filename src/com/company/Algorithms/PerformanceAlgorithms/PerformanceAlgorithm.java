package com.company.Algorithms.PerformanceAlgorithms;

import com.company.Users.UserReviewDataSet;

public abstract class PerformanceAlgorithm {

    UserReviewDataSet generatedUserReviewDataSet;
    UserReviewDataSet actualUserReviewDataSet;

    abstract double compareGeneratedReviewsToUserReviews(UserReviewDataSet generatedUserReviewDataSet, UserReviewDataSet actualUserReviewDataSet);




}
