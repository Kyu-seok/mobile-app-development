package com.yeumkyuseok.mathtest;

public class Result {

    String firstName, lastName, startTime;
    int score, time_taken;

    public Result(String firstName, String lastName, int score, String startTime, int time_taken) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.startTime = startTime;
        this.score = score;
        this.time_taken = time_taken;
    }
}
