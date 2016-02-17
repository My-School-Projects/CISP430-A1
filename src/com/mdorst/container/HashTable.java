package com.mdorst.container;

import com.mdorst.util.function.Function;
import com.mdorst.util.function.QuadFunction;
import com.mdorst.util.function.TriFunction;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Michael Dorst
 * CISP 430
 * Assignment 1 - Hashing
 */

/**
 * ** Class Diagram **
 * HashTable includes Pair
 * 
 * Key : Generic
 * Value : Generic
 *
 * Pair
 *   + key : Key
 *   + value : Value
 *
 * Bucket : list collection of Pair
 *
 * HashTable
 * - hash : Function
 * - table : Array [0..19] of Bucket
 * + add(Key, Value)
 * + search(Key) : Value
 * + search(Key, Function) : Value
 * + iterate(Function)
 * + computeAverageChainLength() : Real
 * + reset()
 * + constructor(Function)
 *
 * ** State Diagram **
 * --- constructor(hashFunction) ---> s0
 * s0 --- add(key, value) ---> s1
 * s1 --- search(key) ---> s1
 * s1 --- search(key, function) ---> s1
 * s1 --- iterate(function) ---> s1
 * s1 --- computeAverageChainLength() ---> s1
 * s1 --- reset() ---> s0
 */

public class HashTable<Key, Value> implements Serializable {
    private class Pair implements Serializable {
        public Key key;
        public Value value;
        public Pair(Key key, Value value) {
            this.key = key;
            this.value = value;
        }
    }
    private Function<Key, Integer> hash;
    private List<List<Pair>> table;

    public void add(Key key, Value value) {
        table.get(hash.apply(key) % 20).add(new Pair(key, value));
    }

    public Value search(Key key) {
        return search(key, (v, b, s) -> {});
    }

    public Value search(Key key, TriFunction<Value, Integer, Integer> f) {
        int bucket = hash.apply(key) % 20;
        for (int slot = 0; slot < table.get(bucket).size(); slot++) {
            Pair pair = table.get(bucket).get(slot);
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
            for (int slot = 0; slot < table.get(bucket).size(); slot++) {
                Pair pair = table.get(bucket).get(slot);
                f.call(pair.key, pair.value, bucket, slot);
            }
        }
    }
    
    public float computeAverageChainLength() {
        float length = 0;
        float chains = 0;
        for (int i = 0; i < 20; i++) {
            if (table.get(i).size() > 1) {
                chains += 1;
                length += table.get(i).size()-1;
            }
        }
        return length / chains;
    }

    public void reset() {
        table = new ArrayList<>(20);
        for (int i = 0; i < 20; i++) {
            table.add(new LinkedList<>());
        }
    }

    public HashTable(Function<Key, Integer> hash) {
        this.hash = hash;
        reset();
    }
}
