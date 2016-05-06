package com.company.Users;


import com.company.Algorithms.RatingAlgorithms.ReviewFillAlgorithm;
import com.company.FileReaderUtility;
import com.company.Reviews.Review;
import com.company.Reviews.UserReview;

import java.io.*;
import java.util.HashMap;
import java.util.NoSuchElementException;

public class UserReviewDataSet extends HashMap<Integer, UserReviewData> implements Serializable {

    ReviewFillAlgorithm reviewFillMethod;

    public static UserReviewDataSet getUserList(String fileName) {
        BufferedReader buf = FileReaderUtility.getBufferedReader(fileName);
        return UserReviewDataSet.getUserListFromReader(buf);
    }

    private static UserReviewDataSet getUserListFromReader(BufferedReader buf) {
        String line;
        UserReviewDataSet users = new UserReviewDataSet();
        try {
            int userId;
            line = buf.readLine();
            while (line != null) {
                String[] columns = line.split("::");
                userId = Integer.valueOf(columns[0]);
                users.addReview(userId, UserReview.getReviewFromColumns(columns));
                line = buf.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return users;
    }

    public void addReview(int userId, Review review) {
        addUserIfFirstReview(userId);
        get(userId).userReviews.put(review.movieId, review);
    }

    private void addUserIfFirstReview(int userId) {

        if(get(userId) == null) {
            put(userId, new UserReviewData(userId));
        }
    }

    public double getAverageRating(int movieId) throws NoSuchElementException {
        return values().stream().mapToDouble(user -> user.userReviews.getMovieRating(movieId))
                .filter(rating -> rating > -1).average().getAsDouble();
    }

    public void setRatingForAllUsersWithoutRating(int movieId, double rating) {
        values().stream().filter(user -> !user.userReviews.isMovieReviewed(movieId))
                .forEach(user1 -> user1.generatedReviews.addReview(movieId, rating));
    }

    public void setRatingAsAverageOfUsersOtherRatings(int movieId) {
        values().forEach(user -> user.setRatingAsAverageOfOtherRatings(movieId));
    }


}

