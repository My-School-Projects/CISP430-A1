package com.mdorst.util.function;

/**
 * This class exists to facilitate "first class" functions, or functions
 * that can be stored, referenced, and passed around.
 */
public interface TriFunction<T, U, V> {
    void call(T t, U u, V v);
}
