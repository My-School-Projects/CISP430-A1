package com.mdorst;

/**
 * Michael Dorst
 * CISP 430
 * Assignment 1 - Hashing
 */
public class Main {
    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();
        taskManager.read("data/datain.txt");
        taskManager.write("data/hashtable.txt");
        taskManager.read("data/hashtable.txt");
        taskManager.displayReport();
        taskManager.search("data/search.txt");
        taskManager.displayStatistics();
    }
}
