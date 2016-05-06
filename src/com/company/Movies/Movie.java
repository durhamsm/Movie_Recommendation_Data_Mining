package com.company.Movies;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Movie {
    public List<GENRE> genres = new ArrayList<>();


    private int id;
    private String title;
    //private String releaseDate;
    //private String imdbUrl;

    public Movie(String[] fieldValues) {
        id = Integer.valueOf(fieldValues[0]);
        title = fieldValues[1];
        //releaseDate = fieldValues[2];
        //imdbUrl = fieldValues[4];
        String genresArray[] = fieldValues[2].split("\\|");
        setGenres(genresArray);
//        setGenres(Arrays.copyOfRange(fieldValues, 5, 24));
    }

    private void setGenres(String[] genreStrings) {
//        int genreIndex = 0;
        for (String genreString: genreStrings) {
            try {
                genres.add(GENRE.valueOf(genreString.toUpperCase().replace("-", "_")));
            } catch (IllegalArgumentException ex) {
                System.out.println("No Genre: " + genreString);
            }
//            if (isGenre.equals("1")) {
//                genres.add(GENRE.values()[genreIndex]);
//            }
//            ++genreIndex;
        }
    }

    public boolean hasCommonGenre(Movie movie) {
        return !movie.genres.stream().filter(genre -> hasGenre(genre)).collect(Collectors.toList()).isEmpty();
    }

    public int getNumGenreMatches(List<GENRE> genresToMatch) {
        return (int)genresToMatch.stream().filter(genre -> hasGenre(genre)).count();
    }

    public boolean hasGenre(GENRE genreToCheck) {
        return genres.contains(genreToCheck);
    }

    public enum GENRE {
        UNKNOWN, ACTION,ADVENTURE,ANIMATION,CHILDREN,COMEDY ,CRIME,DOCUMENTARY ,
        DRAMA ,FANTASY ,FILM_NOIR,HORROR ,MUSICAL ,MYSTERY ,ROMANCE,
        SCI_FI, THRILLER, WAR , WESTERN, IMAX
    }

}