package com.mdorst;

import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * Michael Dorst
 * CISP 430
 * Assignment 1 - Hashing
 */
public class Main {

    static PrintStream out = System.out;

    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();
        taskManager.read();
        taskManager.write();
        taskManager.restore();
        taskManager.search();
        out.println("Average Collision Chain Length:" + taskManager.computeAverageChainLength());
    }
}
