package org.jimbarritt.spikes.rest.collection;

import java.util.*;

public class CollectionTransformation {
    @SuppressWarnings("unchecked")
    public static <T> List<T> asList(Enumeration enumeration) {
        List<T> list = new ArrayList<T>();

        while (enumeration.hasMoreElements()) {
            list.add((T) enumeration.nextElement());
        }
        return list;
    }

    public static <T> Set<T> asHashSet(T... elements) {
        return new HashSet<T>(Arrays.<T>asList(elements));
    }
}
