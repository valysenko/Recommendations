package com.lysenko;

import com.lysenko.services.CollectionService;
import com.lysenko.services.UDKService;
import com.lysenko.utils.MapUtils;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class TestUDK {
    public static void main(String[] args) throws IOException {
        UDKService udkService = new UDKService();
        MapUtils mapUtils = new MapUtils();
        udkService.prepareUDKCollection();
        Map<String, Integer> collection = mapUtils.getSortedMap(udkService.prepareUDKCollection(), "DESC");
        for (Map.Entry<String, Integer> entry : collection.entrySet()) {
            System.out.println(entry.getKey() + "   =   " + entry.getValue());
        }
    }
}
