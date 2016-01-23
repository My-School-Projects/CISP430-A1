package com.mdorst;

import com.mdorst.container.HashTable;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

public class Main {

    public static void main(String[] args) {
        Data datain = new Data("data/datain.txt");
        HashTable<String, String> hashTable = new HashTable<>(new StringHash());
        String line;

        while ((line = datain.getLine()) != null) {
            hashTable.add(line.substring(0, 10), line.substring(10));
        }
        try {
            hashTable.print(new PrintStream("data/dataout.txt", "UTF-8"));
        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        Data dataout = new Data("data/dataout.txt");
        hashTable = new HashTable<>(new StringHash());
        while ((line = dataout.getLine()) != null) {
            if (line.startsWith(" ")) {
                hashTable.add(line.substring(11, 21), line.substring(24));
            }
        }
        try {
            hashTable.print(new PrintStream("data/restored.txt", "UTF-8"));
        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
