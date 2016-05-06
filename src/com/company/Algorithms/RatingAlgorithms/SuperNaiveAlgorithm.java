package com.company.Algorithms.RatingAlgorithms;

import com.company.Reviews.UserReview;
import com.company.Users.UserReviewData;
import com.company.Users.UserReviewDataSet;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SuperNaiveAlgorithm extends ReviewFillAlgorithm {

    public SuperNaiveAlgorithm(UserReviewDataSet userReviewDataSet) {
        super(userReviewDataSet);
    }

    @Override
    public void fillInEmptyReviews() {
        double averageAllMoviesAllUsers = getAverageAllRatings();
        for (int movieId = 1; movieId <= UserReview.numMovies; ++movieId) {
            userReviewDataSet.setRatingForAllUsersWithoutRating(movieId, averageAllMoviesAllUsers);
        }
    }

    public double getAverageAllRatings() {
        List<Double> allRatings = new ArrayList<>();
        for (UserReviewData userReviewData: userReviewDataSet.values()) {
            allRatings.addAll(userReviewData.userReviews.values().stream().map(userReview -> userReview.rating).collect(Collectors.toList()));
        }

        double averageAllMoviesAllUsers = allRatings.stream().mapToDouble(rating -> rating).average().getAsDouble();
        return averageAllMoviesAllUsers;
    }

}