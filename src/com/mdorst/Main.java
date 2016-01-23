package com.mdorst;

import java.io.IOException;
import java.io.PrintStream;

/**
 * Michael Dorst
 * CISP 430
 * Assignment 1 - Hashing
 */
public class Main {

    static PrintStream out = System.out;

    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();
        printMenu();
        try {
            while (true) {
                int ch = System.in.read();
                if (ch == 'x' || ch == 'X') {
                    break;
                }
                if (ch == '1') {
                    taskManager.input();
                }
                if (ch == '2') {
                    taskManager.outputToFile();
                }
                if (ch == '3') {
                    taskManager.outputToConsole();
                }
                if (ch == '4') {
                    taskManager.restore();
                }
                if (ch == '5') {
                    // Search
                }
                if (ch == '6') {
                    // Compute efficiency statistics
                }
                if (ch == '?') {
                    printMenu();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void printMenu() {
        out.println("1 Read from datain.txt");
        out.println("2 Write to report.txt");
        out.println("3 Disply data report");
        out.println("4 Restore data from report.txt");
        out.println("5 Search");
        out.println("6 Compute efficiency statistics");
        out.println("? Display this menu");
        out.println("X Exit");
    }
}
