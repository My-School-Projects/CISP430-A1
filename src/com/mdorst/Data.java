package com.mdorst;

import com.mdorst.container.HashTable;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;

/**
 * Michael Dorst
 */
public class Data {
    private List<String> data;
    private Iterator<String> it;

    public String getLine() {
        if (it.hasNext()) {
            return it.next();
        } else {
            return null;
        }
    }

    public enum Destination {
        toFile,
        toConsole
    }

    public void print(HashTable<String, String, StringHash> hashTable, Destination destination) {
        PrintStream stream = null;
        if (destination == Data.Destination.toFile) {
            try {
                stream = new PrintStream("data/dataout.txt", "UTF-8");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        } else {
            stream = System.out;
        }
        hashTable.print(stream);
    }

    public Data(String path) {
        try {
            data = Files.readAllLines(Paths.get(path), Charset.defaultCharset());
        } catch (IOException e) {
            e.printStackTrace();
        }
        it = data.listIterator();
    }
}
