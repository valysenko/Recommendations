package main.java;

import main.java.services.CollectionService;
import main.java.services.TF_IDFService;
import main.java.utils.MapUtils;

import java.io.IOException;
import java.util.*;

public class Test {

    public static void main(String[] args) throws IOException {
        // services
        CollectionService collectionService = new CollectionService();
        TF_IDFService tfIdfService = new TF_IDFService();
        MapUtils mapUtils = new MapUtils();

        // Collections
        // map: file uuid => list of words
        HashMap<UUID, List<String>> filesMap = collectionService.buildFilesCollection();
        // map: file uuid => map: word ->tf_idf value
        HashMap<UUID, HashMap<String, Double>> tfIdfFilesMap = tfIdfService.calculateTF_IDF_ForCollection(filesMap);
        // map: file uuid => sorted map : word ->tf_idf value
        HashMap<UUID, LinkedHashMap<String, Double>> sortedTfIdfFilesMap = tfIdfService.getSorted_TF_IDF_Collection(tfIdfFilesMap, 10);

        System.out.println();
        for(Map.Entry<UUID, HashMap<String, Double>> entry : tfIdfFilesMap.entrySet()) {
            System.out.println(entry.getKey());
            HashMap<String, Double> words = entry.getValue();
            Map<String, Double> sorted = mapUtils.sort(entry.getValue(), "DESC");
            mapUtils.showN(sorted, 10);

            break;
        }

    }

}
