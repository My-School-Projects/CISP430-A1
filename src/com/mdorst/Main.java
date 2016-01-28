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
        printMenu();
        try {
            while (true) {
                int ch = System.in.read();
                if (ch == 'x' || ch == 'X') {
                    break;
                }
                if (ch == '1') {
                    taskManager.read();
                }
                if (ch == '2') {
                    taskManager.write();
                }
                if (ch == '3') {
                    taskManager.display();
                }
                if (ch == '4') {
                    taskManager.restore();
                }
                if (ch == '5') {
                    out.print("Query: ");
                    Scanner input = new Scanner(System.in);
                    input.nextLine();
                    out.println(taskManager.search(input.nextLine()));
                }
                if (ch == '6') {
                    out.println("Average Collision Chain Length:" + taskManager.computeAverageChainLength());
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
