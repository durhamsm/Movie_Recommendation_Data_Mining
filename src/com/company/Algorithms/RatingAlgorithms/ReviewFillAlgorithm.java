package com.company.Algorithms.RatingAlgorithms;

import com.company.NoMatchesException;
import com.company.Reviews.ReviewComparison;
import com.company.Users.UserReviewData;
import com.company.Users.UserReviewDataSet;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public abstract class ReviewFillAlgorithm {

    protected UserReviewDataSet userReviewDataSet;

    public UserReviewDataSet getUserListFilledIn() {
        fillInEmptyReviews();
        return userReviewDataSet;
    }

    public ReviewFillAlgorithm(UserReviewDataSet userReviewDataSet) {
        this.userReviewDataSet = userReviewDataSet;
    }

    abstract public void fillInEmptyReviews();

//    abstract void fillInMovieReview(UserReviewData userReviewDataToFillIn, int movieId);

}