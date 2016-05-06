package com.company.Algorithms.DistanceAlgorithms;


import com.company.Algorithms.DistanceAlgorithms.DistanceAlgorithm;
import com.company.Users.UserReviewDataSet;

import java.util.List;

public class EuclideanAlgorithm extends DistanceAlgorithm {


    public double computeDistanceFromDifferences(List<Integer> absDifferences) {
        return Math.sqrt(absDifferences.stream().mapToDouble(difference -> difference*difference).sum());
    }

}