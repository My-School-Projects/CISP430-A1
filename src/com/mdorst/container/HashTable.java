package com.mdorst.container;

import com.mdorst.util.function.Function;
import com.mdorst.util.function.QuadFunction;
import com.mdorst.util.function.TriFunction;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

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

    /**
     * Finds the value associated with {@code key}, and returns it.
     * @param key The key to be searched for
     * @return The value associated with {@code key}
     */
    public Value search(Key key) {
        return search(key, (v, b, s) -> {});
    }

    /**
     * Finds the value associated with {@code key}.
     * Calls {@code f}, passing in the value, as well as the
     * indices of the bucket and slot it was found in.
     * Returns the value.
     * @param key The key to be searched for
     * @param f The function to be called upon finding the value associated with {@code key}
     * @return The value associated with {@code key}
     */
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

    /**
     * Iterates over all the elements in the table.
     * Calls {@code f} on each element, passing in its key, its value,
     * and the indices of the bucket and slot where it is located.
     * @param f The function to be called on each element
     */
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
