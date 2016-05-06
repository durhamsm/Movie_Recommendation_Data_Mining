package com.company.Algorithms.PredictionMethods;

import com.company.Movies.Movie;
import com.company.Movies.MovieList;
import com.company.Reviews.GeneratedReviewsList;
import com.company.Reviews.Review;
import com.company.Reviews.ReviewList;
import com.company.Reviews.UserReview;
import com.company.Users.UserReviewData;
import com.company.Users.UserReviewDataSet;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GenreAverageOfUserMethod extends RatingPredictionMethod {

    private static final double MIN_PERCENT_COMMON_GENRES = 0.5;
    private static final int minNumMoviesWithCommonGenres = 3;

    @Override
    public GeneratedReviewsList fill(UserReviewData userReviewData, UserReviewDataSet userReviewDataSet, int movieId) throws Exception {
        setValues(userReviewData, userReviewDataSet, movieId);
        setToGenreAverage();
        return userReviewData.generatedReviews;
    }

    private void setToGenreAverage() throws Exception {
        List<GenreMatch> genreMatches = getGenreMatches();
        List<Integer> sortedMatchingMovieIds = getSortedMatchingMovieIds(genreMatches);

        if (sortedMatchingMovieIds.size() < minNumMoviesWithCommonGenres) {
            throw new Exception();
        }

        double rating = sortedMatchingMovieIds.stream()
                .mapToDouble(rankedMovieId ->  userReviewData.userReviews.get(rankedMovieId).rating)
                .average().getAsDouble();
        userReviewData.generatedReviews.addReview(movieId, rating);
    }

    private List<Integer> getSortedMatchingMovieIds(List<GenreMatch> genreMatches) {
        double minCommonGenres = MIN_PERCENT_COMMON_GENRES * MovieList.movieList.get(movieId).genres.size();

        return genreMatches.stream()
                .filter(genreMatch -> genreMatch.numMatches >= minCommonGenres)
                .sorted((genreMatchLhs, genreMatchRhs) -> Integer.valueOf(genreMatchLhs.numMatches).compareTo(genreMatchRhs.numMatches))
                .map(genreMatch -> genreMatch.movieId).collect(Collectors.toList());
    }

    private List<GenreMatch> getGenreMatches() {
        Movie unRankedMovie = MovieList.getMovie(movieId);
        List<GenreMatch> genreMatches = new ArrayList<>();
        int numGenreMatches;

        for (int rankedMovieId: userReviewData.userReviews.keySet()) {
            numGenreMatches = MovieList.getMovie(rankedMovieId).getNumGenreMatches(unRankedMovie.genres);
            genreMatches.add(new GenreMatch(rankedMovieId, numGenreMatches));
        }

        return genreMatches;
    }

//    private GenreMatch getGenreMatch(Movie.GENRE genre) {
//        GenreMatch genreMatch = new GenreMatch(genre);
//
//
//        return genreMatch;
//    }

}


//
//class GenreMatch {
//
//    private static final int MIN_GENRE_MATCHES = 4;
//
//    private Movie.GENRE genre;
//    private List<Integer> matchedGenreMovieIds = new ArrayList<>();
//
//    public GenreMatch(Movie.GENRE genre) {
//        this.genre = genre;
//    }
//
//    public void addMatch(int movieId) {
//        matchedGenreMovieIds.add(movieId);
//    }
//
//    public Integer getNumMatches() {
//        return matchedGenreMovieIds.size();
//    }
//
//    public List<Integer> getMatchedMovieIds() {
//        return matchedGenreMovieIds;
//    }
//
//    public static GenreMatch getBestGenreMatch(List<GenreMatch> genreMatches) throws Exception {
//        return genreMatches.stream()
//                .filter(genreMatch -> genreMatch.getNumMatches() >= MIN_GENRE_MATCHES)
//                .sorted((genreMatchLhs, genreMatchRhs) -> genreMatchLhs.getNumMatches().compareTo(genreMatchRhs.getNumMatches()))
//                .collect(Collectors.toList())
//                .get(genreMatches.size() - 1);
//    }
//
//}
