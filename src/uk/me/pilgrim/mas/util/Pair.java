package uk.me.pilgrim.mas.util;

import java.util.AbstractMap;

public class Pair<A, B> extends AbstractMap.SimpleEntry<A, B> {
    public Pair(A key, B value) {
        super(key, value);
    }
}