package com.mdorst;

import com.mdorst.container.HashTable;

import java.io.*;

/**
 * Michael Dorst
 * CISP 430
 * Assignment 1 - Hashing
 */

/**
 * ** Class Diagram **
 * Application uses DataInput
 *
 * Application
 * - hashTable : HashTable (Key = String, Value = String)
 *
 * + read(String)
 * + write(String)
 * + restore(String)
 * + displayReport()
 * + search(String)
 * + displayStatistics()
 * + constructor()
 *
 * ** State Diagram **
 * --- constructor() ---> s0
 * s0 --- read(s) ---> s1
 * s0 --- restore(s) ---> s1
 * s1 --- write(s) ---> s1
 * s1 --- search(s) ---> s
 * s1 --- displayReport() ---> s1
 * s1 --- displayStatistics() ---> s1
 */

public class Application {

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
        out.println("Hash Table Verification Report");
        out.println("==============================");
        hashTable.iterate((key, value, bucket, slot) -> {
            if (slot == 0) {
                out.println("Bucket " + (bucket+1));
            }
            out.println("    Slot " + (slot+1) + " : " + key + "   " + value);
        });
        out.println();
    }

    @SuppressWarnings("unchecked")
    public void restore(String path) {
        try {
            ObjectInputStream stream = new ObjectInputStream(new FileInputStream(path));
            hashTable = (HashTable<String, String>) stream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void write(String path) {
        try {
            ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream(path));
            stream.writeObject(hashTable);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void search(String path) {
        String query;
        DataInput search = new DataInput(path);
        out.println("Search and Retrieval Transactions");
        out.println("=================================");
        out.println("Search Key    Record");
        while ((query = search.getLine()) != null) {
            out.print(query + "    ");
            hashTable.search(query, (result, bucket, slot) -> {
                if (result != null) {
                    out.format("%2d/%2d    " + result, bucket, slot);
                    out.println();
                } else {
                    out.println("         Record not found");
                }
            });
        }
        out.println();
    }

    public void displayStatistics() {
        out.println("Average Chain Length = " + hashTable.computeAverageChainLength());
    }

    public Application() {
        hashTable = new HashTable<>(
            (String key) -> key.charAt(1) + key.charAt(3) + key.charAt(5)
    );
        out = System.out;
    }
}
