package com.mdorst.container;

import java.io.PrintStream;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;

/**
 * Micahel Dorst
 * CISP 430
 */
public class HashTable<Key, Value> {
    private class Pair {
        public Key key;
        public Value value;
        public Pair(Key key, Value value) {
            this.key = key;
            this.value = value;
        }
    }
    private Function<Key, Integer> hash;
    private List<Pair>[] table;

    public void add(Key key, Value value) {
        table[hash.apply(key) % 20].add(new Pair(key, value));
    }

    public void print(PrintStream stream) {
        for (int i = 0; i < 20; i++) {
            stream.println("Bucket " + (i+1));
            for (int j = 0; j < table[i].size(); j++) {
                stream.println("  Slot " + (j+1) + " : " + table[i].get(j).key + "   " + table[i].get(j).value);
            }
        }
    }

    public HashTable(Function<Key, Integer> hash) {
        this.hash = hash;
        table = new List[20];
        for (int i = 0; i < 20; i++) {
            table[i] = new LinkedList<>();
        }
    }
}
