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
    public String search(String key) {
        return hashTable.search(key);
    }
    // compute hash efficiency statistics

    public TaskManager() {
        hashTable = new HashTable<>(new StringHash());
    }
}
