package com.company;

//import com.company.Algorithms.ManhattanAlgorithm;
import com.company.Algorithms.DistanceAlgorithms.DistanceAlgorithm;
import com.company.Algorithms.DistanceAlgorithms.EuclideanAlgorithm;
import com.company.Algorithms.DistanceAlgorithms.ManhattanAlgorithm;
import com.company.Algorithms.PerformanceAlgorithms.MADAlgorithm;
import com.company.Algorithms.PredictionMethods.RatingPredictionMethod;
import com.company.Algorithms.RatingAlgorithms.MyReviewFillAlgorithm;
import com.company.Algorithms.RatingAlgorithms.NaiveAlgorithm;
import com.company.Algorithms.RatingAlgorithms.ReviewFillAlgorithm;
import com.company.Algorithms.RatingAlgorithms.SuperNaiveAlgorithm;
import com.company.Algorithms.DistanceAlgorithms.SupremumAlgorithm;
import com.company.Movies.MovieList;
        import com.company.Users.UserList;
import com.company.Users.UserReviewDataSet;

import java.io.*;
import java.util.ArrayList;
import java.util.List;



public class Main {

    public static void main(String[] args) {
        MovieList movieList = new MovieList(FileReaderUtility.getBufferedReader("data/ml-10M100K/movies.dat"));
        UserList userList = new UserList(FileReaderUtility.getBufferedReader("data/ml-100k/u.user"));

        List<TrialRun> trialRuns = new ArrayList<>();
        ReviewFillAlgorithm myAlgorithm;
        DistanceAlgorithm distanceAlgorithm;
        int k;

        DistanceAlgorithm[] distanceAlgorithms = {new EuclideanAlgorithm(), new ManhattanAlgorithm(), new SupremumAlgorithm()};
        int[] kValues = {2, 4, 6};


        for (int testPartition = 1; testPartition < 2; ++testPartition) {
//            String baseName = "data/ml-100k/u" + testPartition + ".base";
            String baseName = "data/ml-10M100K/r" + testPartition + ".train";
            String testName = "data/ml-10M100K/r" + testPartition + ".test";
            UserReviewDataSet actualUserList = UserReviewDataSet.getUserList(baseName);
            UserReviewDataSet userListMyAlg = UserReviewDataSet.getUserList(testName);

            for (int kIndex = 2; kIndex < 3; ++kIndex) {
                k = kValues[kIndex];

                for (int distAlgIndex = 0; distAlgIndex < 1; ++distAlgIndex) {
                    distanceAlgorithm = distanceAlgorithms[distAlgIndex];


                    myAlgorithm = new MyReviewFillAlgorithm(userListMyAlg, distanceAlgorithm, k);
                    userListMyAlg = myAlgorithm.getUserListFilledIn();
                    double performanceScore = new MADAlgorithm().compareGeneratedReviewsToUserReviews(userListMyAlg, actualUserList);
                    trialRuns.add(new TrialRun(testPartition, k, distAlgIndex, performanceScore));

                }
            }
        }

        TrialRun.printTrialRuns(trialRuns, "results_alg2_10M_dataset.txt");


//        writeUserListToFile(userListMyAlg, "userListMyAlg.ser");

    }

    private static UserReviewDataSet readUserListFromSerFile(String fileName) {
        UserReviewDataSet userReviewDataSet = new UserReviewDataSet();
        try {
            FileInputStream fis = new FileInputStream(fileName);
            ObjectInputStream ois = new ObjectInputStream(fis);
            userReviewDataSet = (UserReviewDataSet) ois.readObject();
            ois.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userReviewDataSet;
    }

    private static void writeUserListToFile(UserReviewDataSet userReviewDataSet, String fileName) {
        try {

            FileOutputStream fos = new FileOutputStream(fileName);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(userReviewDataSet);
            oos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}







































