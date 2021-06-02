package com.lcd.birding_backend.services;

import java.util.LinkedHashMap;
import java.util.Map;

public class LRUCache<K, V> extends LinkedHashMap {
    private static final long serialVersionUID = 1L;
    private int lruSize;

    public LRUCache(int lruSize) {
        super(16, 0.75f, true);
        this.lruSize = lruSize;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry eldest) {
        return size() > lruSize;
    }
}
