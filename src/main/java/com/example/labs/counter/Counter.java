package com.example.labs.counter;

public class Counter {
    private static int COUNTER = 0;

    public static synchronized void increment(){
        COUNTER++;
    }

    public static int getCounter(){
        return COUNTER;
    }

}