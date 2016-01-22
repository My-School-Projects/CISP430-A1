package com.mdorst;

import java.util.function.Function;

/**
 * Michael Dorst
 */
public class StringHash implements Function<String, Integer> {
    public Integer apply(String str) {
        return str.charAt(2) + str.charAt(4) + str.charAt(6);
    }
}
