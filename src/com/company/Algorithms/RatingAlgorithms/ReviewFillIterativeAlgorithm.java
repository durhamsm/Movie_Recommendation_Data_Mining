package com.company.Algorithms.RatingAlgorithms;


import com.company.Algorithms.PredictionMethods.RatingPredictionMethod;
import com.company.Users.UserReviewData;
import com.company.Users.UserReviewDataSet;

import java.util.ArrayList;
import java.util.List;

abstract class ReviewFillIterativeAlgorithm extends ReviewFillAlgorithm {

    public List<RatingPredictionMethod> predictionMethods = new ArrayList<>();

    public ReviewFillIterativeAlgorithm(UserReviewDataSet userReviewDataSet) {
        super(userReviewDataSet);
    }

    abstract void setDefaultPredictionMethods();

    public void fillInEmptyReviews() {
        List<Integer> unrankedMovieIds;
        for (UserReviewData userReviewDataToFillIn : userReviewDataSet.values()) {
            unrankedMovieIds = userReviewDataToFillIn.userReviews.getUnrankedMovieIds();
            System.out.println("UserReviewData: " + userReviewDataToFillIn.id + ", unranked: " + unrankedMovieIds.size());
            for (int movieId : unrankedMovieIds) {
                fillInMovieReview(userReviewDataToFillIn, movieId);
            }
            System.out.println("Total Ranked: " + (userReviewDataToFillIn.userReviews.size() + userReviewDataToFillIn.generatedReviews.size()));
        }
    }

    public void fillInMovieReview(UserReviewData userReviewDataToFillIn, int movieId) {
        for (RatingPredictionMethod predictionMethod: predictionMethods) {
            try {
                predictionMethod.fill(userReviewDataToFillIn, userReviewDataSet, movieId);
                predictionMethod.iterateCount();
                break;
            } catch(Exception ex) {

            }
        }
    }

}