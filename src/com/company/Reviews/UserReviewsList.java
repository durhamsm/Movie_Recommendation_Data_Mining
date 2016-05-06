package com.company.Reviews;


import java.util.ArrayList;
import java.util.List;

public class UserReviewsList extends ReviewList {

    public List<Integer> getUnrankedMovieIds() {
        List<Integer> unrankedMovieIds = new ArrayList<Integer>();
        for (int movieId = 1; movieId <= UserReview.numMovies; ++movieId) {
            if (!isMovieReviewed(movieId)){
                unrankedMovieIds.add(movieId);
            }
        }
        return unrankedMovieIds;
    }



}
