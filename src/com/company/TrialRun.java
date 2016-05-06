package com.company;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

public class TrialRun {
    public static final String[] distAlgNames = {"EuclideanAlgorithm", "ManhattanAlgorithm", "SupremumAlgorithm"};

    private int k;
    private int distAlgIndex;
    private int testPartition;
    private double performanceScore;

    public TrialRun(int testPartition, int k, int distAlgIndex, double performanceScore) {
        this.k = k;
        this.distAlgIndex = distAlgIndex;
        this.testPartition = testPartition;
        this.performanceScore = performanceScore;
    }

    public static void printTrialRuns(List<TrialRun> trialRuns, String fileName) {

        try {
            PrintWriter out = new PrintWriter(fileName);
            trialRuns.forEach(trialRun ->
                    out.println(trialRun.k + "\t" + trialRun.getDistAlgorithmName() + "\t" + trialRun.testPartition + "\t" + trialRun.performanceScore));
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private String getDistAlgorithmName() {
        return distAlgNames[distAlgIndex];
    }

}
