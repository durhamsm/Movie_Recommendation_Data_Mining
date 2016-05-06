package com.company.Algorithms.PredictionMethods;

import com.company.Algorithms.DistanceAlgorithms.DistanceAlgorithm;
import com.company.NoMatchesException;
import com.company.Reviews.GeneratedReviewsList;
import com.company.Reviews.ReviewComparison;
import com.company.Users.UserReviewData;
import com.company.Users.UserReviewDataSet;

import java.util.ArrayList;
import java.util.List;

public class UsersWithSimilarRatingsMethod extends KBestMethod {

    public UsersWithSimilarRatingsMethod(int k, int minCommonReviews, DistanceAlgorithm distanceAlgorithm) {
        super(k, minCommonReviews, distanceAlgorithm);
    }

    public GeneratedReviewsList fill(UserReviewData userReviewData, UserReviewDataSet userReviewDataSet, int movieId) throws Exception {
        setValues(userReviewData, userReviewDataSet, movieId);
        addKBestAverage(userReviewData, movieId);
        return userReviewData.generatedReviews;
    }

    public ReviewComparison getReviewComparison(UserReviewData userReviewDataToFillIn, UserReviewData userReviewDataToCompare, int movieId){
        List<Integer> commonMovieIds = userReviewDataToFillIn.userReviews.getCommonMovieIds(userReviewDataToCompare.userReviews);
        double compareScore = calculateDistance(userReviewDataToFillIn, userReviewDataToCompare, commonMovieIds);
        return new ReviewComparison(userReviewDataToCompare, movieId, compareScore);
    }

    public double calculateDistance(UserReviewData userReviewDataToFillIn, UserReviewData userReviewDataToCompare, List<Integer> commonMovieIds) {

        if (commonMovieIds.size() < minCommonReviews) {
            return -1;
        }
        List<Integer> absDifferences = getAbsDifferences(userReviewDataToFillIn, userReviewDataToCompare, commonMovieIds);
        return distanceAlgorithm.computeDistanceFromDifferences(absDifferences);
    }

}
