package com.jimbarritt.spikes.stringtemplate.collection;

import java.util.*;

public class UnsupportedOperationMap<K, V> implements Map<K, V> {
    @Override public int size() {
        throw new UnsupportedOperationException();
    }

    @Override public boolean isEmpty() {
        throw new UnsupportedOperationException();
    }

    @Override public boolean containsKey(Object key) {
        throw new UnsupportedOperationException();
    }

    @Override public boolean containsValue(Object value) {
        throw new UnsupportedOperationException();
    }

    @Override public V get(Object key) {
        throw new UnsupportedOperationException();
    }

    @Override public V put(K key, V value) {
        throw new UnsupportedOperationException();
    }

    @Override public V remove(Object key) {
        throw new UnsupportedOperationException();
    }

    @Override public void putAll(Map<? extends K, ? extends V> m) {
        throw new UnsupportedOperationException();
    }

    @Override public void clear() {
        throw new UnsupportedOperationException();
    }

    @Override public Set<K> keySet() {
        throw new UnsupportedOperationException();
    }

    @Override public Collection<V> values() {
        throw new UnsupportedOperationException();
    }

    @Override public Set<Entry<K, V>> entrySet() {
        throw new UnsupportedOperationException();
    }

    @Override public boolean equals(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override public int hashCode() {
        throw new UnsupportedOperationException();
    }
}