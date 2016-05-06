package com.company.Algorithms.PredictionMethods;

import com.company.Algorithms.DistanceAlgorithms.DistanceAlgorithm;
import com.company.NoMatchesException;
import com.company.Reviews.GeneratedReviewsList;
import com.company.Reviews.ReviewComparison;
import com.company.Users.UserReviewData;
import com.company.Users.UserReviewDataSet;

import java.util.ArrayList;
import java.util.List;

public abstract class KBestMethod extends RatingPredictionMethod {

    public int k;
    public int minCommonReviews;
    public DistanceAlgorithm distanceAlgorithm;

    public KBestMethod(int k, int minCommonReviews, DistanceAlgorithm distanceAlgorithm) {
        this.k = k;
        this.minCommonReviews = minCommonReviews;
        this.distanceAlgorithm = distanceAlgorithm;
    }

    abstract ReviewComparison getReviewComparison(UserReviewData userReviewDataToFillIn, UserReviewData userReviewDataToCompare, int movieId);


    protected void addKBestAverage(UserReviewData userReviewDataToFillIn, int movieId) throws NoMatchesException {
        List<ReviewComparison> reviewComparisons = compareReviews(userReviewDataToFillIn, movieId);
        List<ReviewComparison> bestReviewComparisons = ReviewComparison.getKBestReviewComparisons(reviewComparisons, k);
        double calculatedRating = ReviewComparison.calculateAverageRating(bestReviewComparisons, movieId);
        userReviewDataToFillIn.generatedReviews.addReview(movieId, calculatedRating);
    }

    private List<ReviewComparison> compareReviews(UserReviewData userReviewDataToFillIn, int movieId) {
        List<ReviewComparison> reviewComparisons = new ArrayList<>();
        for (UserReviewData userReviewDataToCompare : userReviewDataSet.values()) {
            if (userReviewDataToFillIn.id != userReviewDataToCompare.id && userReviewDataToCompare.userReviews.isMovieReviewed(movieId)) {
                reviewComparisons.add(getReviewComparison(userReviewDataToFillIn, userReviewDataToCompare, movieId));
            }
        }
        return reviewComparisons;
    }

    protected List<Integer> getAbsDifferences(UserReviewData userReviewDataToFillIn, UserReviewData userReviewDataToCompare, List<Integer> commonMovieIds) {
        List<Integer> absDifferences = new ArrayList<>();
        int difference;
        for (int movieId: commonMovieIds) {
            difference = (int)(Math.abs(userReviewDataToFillIn.userReviews.get(movieId).rating - userReviewDataToCompare.userReviews.get(movieId).rating));
            absDifferences.add(difference);
        }
        return absDifferences;
    }

}
