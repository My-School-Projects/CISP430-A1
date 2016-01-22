package com.mdorst.container;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;

/**
 * Micahel Dorst
 * CISP 430
 */
public class HashTable<Key, Value, HashFunction extends Function<Key, Integer>> {
    private class Pair {
        public Key key;
        public Value value;
        public Pair(Key key, Value value) {
            this.key = key;
            this.value = value;
        }
    }
    private HashFunction hash;
    private List<Pair>[] table;

    public void add(Key key, Value value) {
        table[hash.apply(key) % 20].add(new Pair(key, value));
    }

    public void debug() {
        for (int i = 0; i < 20; i++) {
            System.out.println("Bucket " + (i+1));
            for (int j = 0; j < table[i].size(); j++) {
                System.out.println("  Slot " + (j+1) + " : " + table[i].get(j).key + "   " + table[i].get(j).value);
            }
        }
    }

    public HashTable(HashFunction hash) {
        this.hash = hash;
        table = new List[20];
        for (int i = 0; i < 20; i++) {
            table[i] = new LinkedList<>();
        }
    }
}
