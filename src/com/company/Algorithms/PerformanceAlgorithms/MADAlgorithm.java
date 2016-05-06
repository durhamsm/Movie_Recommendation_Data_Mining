package com.company.Algorithms.PerformanceAlgorithms;

import com.company.Reviews.ReviewList;
import com.company.Users.UserReviewDataSet;

import java.util.ArrayList;
import java.util.List;

public class MADAlgorithm extends PerformanceAlgorithm {


    @Override
    public double compareGeneratedReviewsToUserReviews(UserReviewDataSet generatedUserReviewDataSet, UserReviewDataSet actualUserReviewDataSet) {
        this.generatedUserReviewDataSet = generatedUserReviewDataSet;
        this.actualUserReviewDataSet = actualUserReviewDataSet;

        return getMeanAbsoluteDifference();
    }

    private double getMeanAbsoluteDifference() {
        List<Double> absoluteDifferencesAllUsers = new ArrayList<>();
        for (int generatedUserId: generatedUserReviewDataSet.keySet()) {
            absoluteDifferencesAllUsers.addAll(getUserAbsoluteDifferences(generatedUserId));
        }
        return getAverage(absoluteDifferencesAllUsers);
    }

    private List<Double> getUserAbsoluteDifferences(int userId) {
        ReviewList generatedReviewList = generatedUserReviewDataSet.get(userId).generatedReviews;
        ReviewList actualReviewList = actualUserReviewDataSet.get(userId).userReviews;
        List<Double> userAbsoluteDifferences = new ArrayList<>();
        double difference, generatedRating, actualRating;

        for (int generatedReviewMovieId: generatedReviewList.keySet()) {
            if (!actualReviewList.isMovieReviewed(generatedReviewMovieId)) {
                continue;
            }
            generatedRating = generatedReviewList.get(generatedReviewMovieId).rating;
            actualRating = actualReviewList.get(generatedReviewMovieId).rating;
            difference = Math.abs(generatedRating - actualRating);
            userAbsoluteDifferences.add(difference);
        }
        return userAbsoluteDifferences;
    }

    private double getAverage(List<Double> numbers) {
        double sum = 0;
        for (double number: numbers) {
            sum += number;
        }
        return sum/numbers.size();
    }


}
