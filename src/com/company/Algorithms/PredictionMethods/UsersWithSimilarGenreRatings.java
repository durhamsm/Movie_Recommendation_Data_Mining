package com.company.Algorithms.PredictionMethods;

import com.company.Algorithms.DistanceAlgorithms.DistanceAlgorithm;
import com.company.Movies.Movie;
import com.company.Movies.MovieList;
import com.company.Reviews.GeneratedReviewsList;
import com.company.Reviews.ReviewComparison;
import com.company.Users.UserReviewData;
import com.company.Users.UserReviewDataSet;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UsersWithSimilarGenreRatings extends KBestMethod {

    private static final double MIN_PERCENT_COMMON_GENRES = 0.5;

    public UsersWithSimilarGenreRatings(int k, int minCommonReviews, DistanceAlgorithm distanceAlgorithm) {
        super(k, minCommonReviews, distanceAlgorithm);
    }

    @Override
    public GeneratedReviewsList fill(UserReviewData userReviewData, UserReviewDataSet userReviewDataSet, int movieId) throws Exception {
        setValues(userReviewData, userReviewDataSet, movieId);
        addKBestAverage(userReviewData, movieId);
        return userReviewData.generatedReviews;
    }

    @Override
    ReviewComparison getReviewComparison(UserReviewData userReviewDataToFillIn, UserReviewData userReviewDataToCompare, int movieId) {
        List<Integer> commonMovieIds = userReviewDataToFillIn.userReviews.getCommonMovieIds(userReviewDataToCompare.userReviews);
        double score = calculateDistance(userReviewDataToFillIn, userReviewDataToCompare, commonMovieIds);
        return new ReviewComparison(userReviewDataToCompare, movieId, score);
    }

    public double calculateDistance(UserReviewData userReviewDataToFillIn, UserReviewData userReviewDataToCompare, List<Integer> commonMovieIds) {
        List<GenreMatch> genreMatches = getGenreMatches(commonMovieIds);
        List<Integer> sortedMatchingMovieIds = getSortedMatchingMovieIds(genreMatches);

        if (sortedMatchingMovieIds.size() < minCommonReviews) {
            return -1;
        }

        List<Integer> absDifferences = getAbsDifferences(userReviewDataToFillIn, userReviewDataToCompare, sortedMatchingMovieIds.subList(0, minCommonReviews));
        return distanceAlgorithm.computeDistanceFromDifferences(absDifferences);
    }

    private List<GenreMatch> getGenreMatches(List<Integer> commonMovieIds) {
        List<GenreMatch> genreMatches = new ArrayList<>();
        Movie commonMovie;
        int numGenreMatches;

        for (int commonMovieId: commonMovieIds) {
            commonMovie = MovieList.movieList.get(commonMovieId);
            numGenreMatches = MovieList.movieList.get(movieId).getNumGenreMatches(commonMovie.genres);
            genreMatches.add(new GenreMatch(commonMovieId, numGenreMatches));
        }
        return genreMatches;
    }

    private List<Integer> getSortedMatchingMovieIds(List<GenreMatch> genreMatches) {
        double minCommonGenres = MIN_PERCENT_COMMON_GENRES * MovieList.movieList.get(movieId).genres.size();

        List<Integer> movieIdsSortedByNumMatches = genreMatches.stream().filter(genreMatch -> genreMatch.numMatches >= minCommonGenres)
                .sorted((genreMatchLhs, genreMatchRhs) -> Integer.valueOf(genreMatchRhs.numMatches).compareTo(genreMatchLhs.numMatches))
                .map(genreMatch -> genreMatch.movieId).collect(Collectors.toList());
        return movieIdsSortedByNumMatches;
    }

}


