package com.mdorst;

import com.mdorst.container.HashTable;

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
    public void input() {
        Data datain = new Data("data/datain.txt");
        String line;
        while ((line = datain.getLine()) != null) {
            hashTable.add(line.substring(0, 10), line.substring(10));
        }
    }
    public void outputToConsole() {
        hashTable.print(System.out);
    }
    // output to dataout.txt
    // restore from dataout.txt
    // search
    // compute hash efficiency statistics

    public TaskManager() {
        hashTable = new HashTable<>(new StringHash());
    }
}
