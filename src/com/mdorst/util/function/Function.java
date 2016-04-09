package com.mdorst.util.function;

import java.io.Serializable;

/**
 * This class exists to facilitate "first class" functions, or functions
 * that can be stored, referenced, and passed around.
 */
public interface Function<T, R> extends Serializable {
    R apply(T t);
}
