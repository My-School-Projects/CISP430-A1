package com.mdorst;

import com.mdorst.container.HashTable;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

public class Main {

    public static void main(String[] args) {
        Data data = new Data("data/datain.txt");
        HashTable<String, String, StringHash> hashTable = new HashTable<>(new StringHash());
        String line;
        while ((line = data.getLine()) != null) {
            hashTable.add(line.substring(0, 9), line.substring(10));
        }
        try {
            hashTable.print(new PrintStream("data/dataout.txt", "UTF-8"));
        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        hashTable.print(System.out);
    }
}
