package com.company.Algorithms.DistanceAlgorithms;


import com.company.Algorithms.DistanceAlgorithms.DistanceAlgorithm;
import com.company.Users.UserReviewDataSet;

import java.util.List;

public class ManhattanAlgorithm extends DistanceAlgorithm {

    public double computeDistanceFromDifferences(List<Integer> absDifferences) {
        return absDifferences.stream().mapToInt(difference -> difference).sum();
    }

}