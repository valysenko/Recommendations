package com.lysenko.utils;

import com.lysenko.entities.Document;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;


public class MapUtils {

    private <K,V extends Comparable<? super V>> List<Map.Entry<K,V>> getSortedList(Map<K,V> map, String order) {
        List<Map.Entry<K,V>> list = new LinkedList<Map.Entry<K,V>>(map.entrySet());

        list.sort(new Comparator<Entry<K,V>>()
        {
            public int compare(Entry<K,V> o1, Entry<K,V> o2) {
                if (order.equals("ASC")) {
                    return o1.getValue().compareTo(o2.getValue());
                }
                else {
                    return o2.getValue().compareTo(o1.getValue());
                }
            }
        });

        return list;
    }

    public <K,V extends Comparable<? super V>> Map<K,V> getSortedMap(Map<K,V> map, String order) {

        // 1. Sort list based on values
        List<Map.Entry<K,V>> list = this.getSortedList(map, order);

        // 2. Create map based on sorted list
        Map<K,V> sortedMap = new LinkedHashMap<K,V>();
        for (Entry<K,V> entry : list) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }

        return sortedMap;
    }

    public <K,V extends Comparable<? super V>> LinkedHashMap<K,V> getSortedMapOfNElements(Map<K,V> map, String order, int n) {

        // 1. Sort list based on values
        List<Map.Entry<K,V>> list = this.getSortedList(map, order);

        // 2. Create map based on sorted list
        LinkedHashMap<K,V> sortedMap = new LinkedHashMap<K,V>();
        int i =0;
        for (Entry<K,V> entry : list) {
            if (i == n) {
                break;
            }
            sortedMap.put(entry.getKey(), entry.getValue());
            i++;
        }

        return sortedMap;
    }

    public <K,V extends Comparable<? super V>> void sortNInDocument(Document document,String order, int n) {

        // 1. Sort list based on values
        List<Map.Entry<K,V>> list = this.getSortedList((Map<K, V>) document.getTfIdfMap(), order);

        // 2. Create map based on sorted list
        int i =0;
        for (Entry<K,V> entry : list) {
            if (i == n) {
                break;
            }
            document.putTfIdfSortedItem((String)entry.getKey(), (Double)entry.getValue());
            i++;
        }

    }

    public <K,V extends Comparable<? super V>> void show(Map<K, V> map) {
        for (Entry<K, V> entry : map.entrySet()) {
            System.out.println(entry.getKey() + " : "+ entry.getValue());
        }
    }

}
