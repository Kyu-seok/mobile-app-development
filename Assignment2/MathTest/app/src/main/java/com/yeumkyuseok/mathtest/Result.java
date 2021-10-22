package com.yeumkyuseok.mathtest;

public class Result {

    String fullName, startTime, time_taken;
    int score;

    public Result(String fullName, int score, String startTime, String time_taken) {
        this.fullName = fullName;
        this.startTime = startTime;
        this.score = score;
        this.time_taken = time_taken;
    }
}
