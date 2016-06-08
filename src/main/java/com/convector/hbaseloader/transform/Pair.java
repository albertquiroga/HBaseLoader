package com.convector.hbaseloader.transform;

/**
 * Created by pyro_ on 03/05/2016.
 */
public class Pair {

    private Enum first;
    private String second;

    public Pair(Enum e, String s) {
        first = e;
        second = s;
    }

    public Enum getFirst() {
        return first;
    }

    public String getSecond() {
        return second;
    }

    public String toString() {
        return first.toString()+":"+second;
    }
}
