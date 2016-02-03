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

    /**
     * Input from datain.txt
     */
    public void read() {
        hashTable = new HashTable<>(new StringHash());
        Data datain = new Data("data/datain.txt");
        String line;
        while ((line = datain.getLine()) != null) {
            hashTable.add(line.substring(0, 10), line.substring(10));
        }
    }
    public void display() {
        hashTable.print(System.out);
    }
    public void write() {
        try {
            hashTable.print(new PrintStream("data/report.txt", "UTF-8"));
        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
    public void restore() {
        String line;
        Data dataout = new Data("data/report.txt");
        hashTable = new HashTable<>(new StringHash());
        while ((line = dataout.getLine()) != null) {
            if (line.startsWith(" ")) {
                hashTable.add(line.substring(11, 21), line.substring(24));
            }
        }
    }
    public void search() {
        String query;
        String result;
        Data search = new Data("data/search.txt");
        out.println("Search and Retrieval Transactions");
        out.println("Search Key    Record");
        while ((query = search.getLine()) != null) {
            out.print(query + "    ");
            if ((result = hashTable.search(query)) != null) {
                out.println(result);
            } else {
                out.println("Record not found");
            }
        }
    }

    public float computeAverageChainLength() {
        return hashTable.computeAverageChainLength();
    }

    public TaskManager() {
        hashTable = new HashTable<>(new StringHash());
        out = System.out;
    }
}
