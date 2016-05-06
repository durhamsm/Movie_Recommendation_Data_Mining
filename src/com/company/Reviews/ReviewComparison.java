package com.company.Reviews;

import com.company.NoMatchesException;
import com.company.Users.UserReviewData;

import java.util.List;
import java.util.stream.Collectors;

public class ReviewComparison {
    public UserReviewData userReviewData;
    public double score;
    public int movieId;

    public ReviewComparison(UserReviewData userReviewData, int movieId, double score) {
        this.userReviewData = userReviewData;
        this.score = score;
        this.movieId = movieId;
    }

    public static List<ReviewComparison> getKBestReviewComparisons(List<ReviewComparison> reviewComparisons, int k)
        throws NoMatchesException {

        reviewComparisons = reviewComparisons.stream()
                .filter(reviewComparison -> reviewComparison.score > -0.5).collect(Collectors.toList());
        reviewComparisons.sort(
                (reviewComp1, reviewComp2) -> new Double(reviewComp1.score).compareTo(new Double(reviewComp2.score)));

        if (reviewComparisons.size() == 0) {
            throw new NoMatchesException();
        }

        int numMatchesToRetrieve = reviewComparisons.size() >= k ? k : reviewComparisons.size();
        return reviewComparisons.subList(0, numMatchesToRetrieve);
    }

    public static double calculateAverageRating(List<ReviewComparison> users, int movieId) {
        return users.stream().mapToDouble(reviewComparison ->
                reviewComparison.userReviewData.userReviews.get(movieId).rating).average().getAsDouble();
    }

}

