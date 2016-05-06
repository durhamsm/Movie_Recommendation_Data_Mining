package com.company.Algorithms.RatingAlgorithms;

import com.company.Users.UserReviewData;
import com.company.Users.UserReviewDataSet;
import com.company.Reviews.UserReview;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class NaiveAlgorithm extends ReviewFillAlgorithm {

    public NaiveAlgorithm(UserReviewDataSet userReviewDataSet) {
        super(userReviewDataSet);
    }

    public void fillInEmptyReviews() {

        double averageRating;

        for (int movieId = 1; movieId <= UserReview.numMovies; ++movieId) {
            try {
                averageRating = userReviewDataSet.getAverageRating(movieId);
                userReviewDataSet.setRatingForAllUsersWithoutRating(movieId, averageRating);
            } catch (NoSuchElementException ex) {
                userReviewDataSet.setRatingAsAverageOfUsersOtherRatings(movieId);
            }

        }
    }

}