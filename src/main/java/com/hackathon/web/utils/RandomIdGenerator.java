package com.hackathon.web.utils;


import java.util.Random;

public class RandomIdGenerator {

    public static Long randomNegativeId() {
        Random rand = new Random();
        return -1 * ((long) rand.nextInt(1000));
    }
    public static Long randomMemberId() {
        Random rand = new Random();
        return (long) (rand.nextInt(1000) + 1);
    }
}
