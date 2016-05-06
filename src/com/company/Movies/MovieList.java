package com.company.Movies;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;

public class MovieList extends HashMap<Integer, Movie> {

    public static MovieList movieList;

    public MovieList(BufferedReader buf) {
        String line;
        try {
            line = buf.readLine();
            while (line != null) {
                String[] columns = line.split("::");
                put(Integer.valueOf(columns[0]), new Movie(columns));
                line = buf.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        movieList = this;
    }

    public static Movie getMovie(int movieId) {
        return movieList.get(movieId);
    }


}
