package com.mdorst;

/**
 * Michael Dorst
 * CISP 430
 * Assignment 1 - Hashing
 */
public class Main {
    public static void main(String[] args) {
        Application app = new Application();
        app.read("data/datain.txt");
        app.write("data/hashtable.data");
        app.restore("data/hashtable.data");
        app.displayReport();
        app.search("data/search.txt");
        app.displayStatistics();
    }
}
