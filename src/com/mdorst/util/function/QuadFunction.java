package com.mdorst.util.function;

/**
 * Michael Dorst
 */
public interface QuadFunction<T, U, V, W> {
    public void call(T t, U u, V v, W w);
}
