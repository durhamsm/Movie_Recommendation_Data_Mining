package com.company.Algorithms.DistanceAlgorithms;


        import com.company.Algorithms.DistanceAlgorithms.DistanceAlgorithm;
        import com.company.Users.UserReviewDataSet;

        import java.util.List;

public class SupremumAlgorithm extends DistanceAlgorithm {

    public double computeDistanceFromDifferences(List<Integer> absDifferences) {
        absDifferences.sort((diff1, diff2) -> new Integer(diff1).compareTo(new Integer(diff2)));
        return absDifferences.get(absDifferences.size() - 1);
    }

}