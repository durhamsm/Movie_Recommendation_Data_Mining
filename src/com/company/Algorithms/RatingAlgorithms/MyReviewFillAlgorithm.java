package com.company.Algorithms.RatingAlgorithms;

import com.company.Algorithms.DistanceAlgorithms.DistanceAlgorithm;
import com.company.Algorithms.DistanceAlgorithms.EuclideanAlgorithm;
import com.company.Algorithms.PredictionMethods.*;
import com.company.Users.UserReviewDataSet;

import java.util.List;

public class MyReviewFillAlgorithm extends ReviewFillIterativeAlgorithm {

    private DistanceAlgorithm distanceAlgorithm;
    private int k;


    public MyReviewFillAlgorithm(UserReviewDataSet userReviewDataSet) {
        super(userReviewDataSet);
        setDefaultPredictionMethods();
    }

    public MyReviewFillAlgorithm(UserReviewDataSet userReviewDataSet, DistanceAlgorithm distanceAlgorithm, int k) {
        super(userReviewDataSet);
        this.distanceAlgorithm = distanceAlgorithm;
        this.k = k;
        setDefaultPredictionMethods();
    }

    public MyReviewFillAlgorithm(UserReviewDataSet userReviewDataSet, List<RatingPredictionMethod> predictionMethods) {
        this(userReviewDataSet);
        this.predictionMethods = predictionMethods;
    }

    public void setDefaultPredictionMethods() {
        predictionMethods.add(new UsersWithSimilarRatingsMethod(6, 4, distanceAlgorithm));
        predictionMethods.add(new UsersWithSimilarGenreRatings(6, 3, new EuclideanAlgorithm()));
        predictionMethods.add(new GenreAverageOfUserMethod());
        predictionMethods.add(new AverageAllUsersForMovieMethod());
        predictionMethods.add(new AverageFromAllMoviesOfUserMethod());
    }

}
