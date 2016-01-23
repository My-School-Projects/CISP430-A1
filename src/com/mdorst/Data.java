package com.mdorst;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;

/**
 * Michael Dorst
 */
public class Data {
    private List<String> data;
    private Iterator<String> it;

    public String getLine() {
        if (it.hasNext()) {
            return it.next();
        } else {
            return null;
        }
    }

    public Data(String path) {
        try {
            data = Files.readAllLines(Paths.get(path), Charset.defaultCharset());
        } catch (IOException e) {
            e.printStackTrace();
        }
        it = data.listIterator();
    }
}
