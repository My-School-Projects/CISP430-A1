package com.mdorst.util.function;

import java.io.Serializable;

/**
 * Michael Dorst
 */
public interface Function<T, R> extends Serializable {
    public R apply(T t);
}
