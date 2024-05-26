package org.example;

import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Random;

@NoArgsConstructor
public class Assignment {

    private String assignmentId;
    private String assignmentName;
    private double weight;
    private int maxScore;
    private double assignmentAverage;
    private ArrayList<Integer> scores;

    private static int nextId = 1;

    public Assignment(String assignmentId, String assignmentName, double weight, int maxScore,
                      double assignmentAverage, ArrayList<Integer> scores) {
        this.assignmentId = assignmentId;
        this.assignmentName = assignmentName;
        this.weight = weight;
        this.maxScore = maxScore;
        this.assignmentAverage = assignmentAverage;
        this.scores = scores;
    }

    public void calcAssignmentAvg() {
        if (scores.isEmpty()) {
            this.assignmentAverage = 0.0;
        } else {
            int sum = 0;
            for (int score : scores) {
                sum += score;
            }
            this.assignmentAverage = (double) sum / scores.size();
        }
    }

    public void generateRandomScore(int numsStudents) {
        Random rand = new Random();
        for (int i = 0; i < numsStudents; i++) {
            int randomScore = rand.nextInt(0,11);

            int score;

            if (randomScore == 0) {
                score = rand.nextInt(60);
            } else if (randomScore <= 2) {
                score = 60 + rand.nextInt(10);
            } else if (randomScore <= 4) {
                score = 70 + rand.nextInt(10);
            } else if (randomScore <= 8) {
                score = 80 + rand.nextInt(10);
            } else {
                score = 90 + rand.nextInt(11);
            }
            scores.add(score);
        }
        calcAssignmentAvg();
    }

    @Override
    public String toString() {
        return String.format("Assignment{id='%s', name='%s', weight=%.2f, maxScore=%d, average=%.2f}",
                assignmentId, assignmentName, weight, maxScore, assignmentAverage);
    }
}
