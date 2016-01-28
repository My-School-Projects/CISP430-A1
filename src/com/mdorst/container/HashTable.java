package com.mdorst.container;

import java.io.PrintStream;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;

/**
 * Michael Dorst
 * CISP 430
 * Assignment 1 - Hashing
 */

/**
 * HashTable includes Pair
 * 
 * Key : Generic
 * Value : Generic
 * 
 * HashTable
 * - hash : Function
 * - table : Array [0..19] of Bucket
 * + add(Key, Value)
 * + search(Key) : Value
 * + print(PrintStream)
 * + computeAverageChainLength() : real
 * 
 * Pair
 *   + key : Key
 *   + value : Value
 * 
 * Bucket : list collection of Pair
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

    public Value search(Key key) {
        for (Pair pair : table[hash.apply(key) % 20]) {
            if (key.equals(pair.key)) {
                return pair.value;
            }
        }
        return null;
    }

    public void print(PrintStream stream) {
        for (int i = 0; i < 20; i++) {
            stream.println("Bucket " + (i+1));
            for (int j = 0; j < table[i].size(); j++) {
                stream.println("  Slot " + (j+1) + " : " + table[i].get(j).key + "   " + table[i].get(j).value);
            }
        }
    }
    
    public float computeAverageChainLength() {
        float length = 0;
        for (int i = 0; i < 20; i++) {
            length += table[i].size();
        }
        return length / 20;
    }

    public HashTable(Function<Key, Integer> hash) {
        this.hash = hash;
        table = new List[20];
        for (int i = 0; i < 20; i++) {
            table[i] = new LinkedList<>();
        }
    }
}
