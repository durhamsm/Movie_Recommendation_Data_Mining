package com.company.Reviews;

public class UserReview extends Review {
    private int timestamp;
    public static int numMovies = 0;

    public UserReview(int movieId, double rating, int timestamp) {
        super(movieId, rating);
        this.timestamp = timestamp;
    }



    public static UserReview getReviewFromColumns(String[] columns) {
        int movieId = Integer.valueOf(columns[1]);
        double rating = Double.valueOf(columns[2]);
        int timestamp = Integer.valueOf(columns[3]);

        numMovies = movieId > numMovies ? movieId : numMovies;

        return new UserReview(movieId, rating, timestamp);
    }
}
