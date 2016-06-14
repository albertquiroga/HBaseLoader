package com.convector.hbaseloader.transform;

/**
 * Created by pyro_ on 03/05/2016.
 */
public class Pair <K,V>{

    private K first;
    private V second;

    public Pair(K e, V s) {
        first = e;
        second = s;
    }

    public K getFirst() {
        return first;
    }

    public V getSecond() {
        return second;
    }

    public String toString() {
        return first.toString()+":"+second;
    }
}
