package com.mdorst.container;

import com.mdorst.util.function.QuadFunction;
import com.mdorst.util.function.TriFunction;

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
 * + printReport(PrintStream)
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
        return search(key, (v, b, s) -> {});
    }

    public Value search(Key key, TriFunction<Value, Integer, Integer> f) {
        int bucket = hash.apply(key) % 20;
        for (int slot = 0; slot < table[bucket].size(); slot++) {
            Pair pair = table[bucket].get(slot);
            if (key.equals(pair.key)) {
                f.call(pair.value, bucket, slot);
                return pair.value;
            }
        }
        f.call(null, null, null);
        return null;
    }

    public void iterate(QuadFunction<Key, Value, Integer, Integer> f) {
        for (int bucket = 0; bucket < 20; bucket++) {
            for (int slot = 0; slot < table[bucket].size(); slot++) {
                Pair pair = table[bucket].get(slot);
                f.call(pair.key, pair.value, bucket, slot);
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

    public void reset() {
        table = new List[20];
        for (int i = 0; i < 20; i++) {
            table[i] = new LinkedList<>();
        }
    }

    public HashTable(Function<Key, Integer> hash) {
        this.hash = hash;
        reset();
    }
}
