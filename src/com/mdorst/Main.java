package com.mdorst;

/**
 * Michael Dorst
 * CISP 430
 * Assignment 1 - Hashing
 */
public class Main {

    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();
        taskManager.input();
        taskManager.outputToFile();
        taskManager.clear();
        taskManager.outputToConsole();
        taskManager.restore();
        taskManager.outputToConsole();
    }
}
