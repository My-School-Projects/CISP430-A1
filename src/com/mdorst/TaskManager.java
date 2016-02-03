package com.mdorst;

import com.mdorst.container.HashTable;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

/**
 * Michael Dorst
 * CISP 430
 * Assignment 1 - Hashing
 */
public class TaskManager {

    static PrintStream out;
    HashTable<String, String> hashTable;

    public void read(String path) {
        hashTable.reset();
        DataInput datain = new DataInput(path);
        String line;
        while ((line = datain.getLine()) != null) {
            hashTable.add(line.substring(0, 10), line.substring(10));
        }
    }
    public void displayReport() {
        hashTable.printReport(out);
        out.println();
    }
    public void write(String path) {
        try {
            hashTable.printData(new PrintStream(path, "UTF-8"));
        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
    public void search(String path) {
        String query;
        String result;
        DataInput search = new DataInput(path);
        out.println("Search and Retrieval Transactions");
        out.println("=================================");
        out.println("Search Key    Record");
        while ((query = search.getLine()) != null) {
            out.print(query + "    ");
            if ((result = hashTable.search(query)) != null) {
                out.println(result);
            } else {
                out.println("Record not found");
            }
        }
        out.println();
    }
    public void displayStatistics() {
        out.println(hashTable.computeAverageChainLength());
    }
    public TaskManager() {
        hashTable = new HashTable<>(
            (String key) -> {
                if (key.length() > 6) {
                    return key.charAt(2) + key.charAt(4) + key.charAt(6);
                }
                if (key.length() > 4) {
                    return key.charAt(2) + key.charAt(4);
                }
                if (key.length() > 2) {
                    return (int) key.charAt(2);
                }
                return 0;
            }
    );
        out = System.out;
    }
}
