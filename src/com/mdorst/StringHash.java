package com.mdorst;

import java.util.function.Function;

/**
 * Michael Dorst
 * CISP 430
 * Assignment 1 - Hashing
 */
public class StringHash implements Function<String, Integer> {
    public Integer apply(String str) {
        if (str.length() > 6) {
            return str.charAt(2) + str.charAt(4) + str.charAt(6);
        }
        if (str.length() > 4) {
            return str.charAt(2) + str.charAt(4);
        }
        if (str.length() > 2) {
            return (int) str.charAt(2);
        }
        return 0;
    }
}
