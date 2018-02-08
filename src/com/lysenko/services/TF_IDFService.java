package com.lysenko.services;

import com.lysenko.entities.Document;
import com.lysenko.utils.MapUtils;

import java.util.*;

public class TF_IDFService {

    private MapUtils mapUtils;

    public TF_IDFService() {
        this.mapUtils = new MapUtils();
    }

//    public HashMap<UUID, LinkedHashMap<String, Double>> getSorted_TF_IDF_Collection(HashMap<UUID, HashMap<String, Double>> tfIdfFilesMap, int n) {
//        HashMap<UUID, LinkedHashMap<String, Double>> sortedTfIdfFilesMap = new HashMap<UUID, LinkedHashMap<String, Double>>();
//
//        for(Map.Entry<UUID, HashMap<String, Double>> entry : tfIdfFilesMap.entrySet()) {
//            UUID key = entry.getKey();
//            HashMap<String, Double> words = entry.getValue();
//            LinkedHashMap<String, Double> sorted = mapUtils.sortN(words, "DESC", n);
//            sortedTfIdfFilesMap.put(key, sorted);
//        }
//
//        return sortedTfIdfFilesMap;
//    }

    private Double calculateTF(List<String> set, String word) {
        double n = 0;
        for (String s : set) {
            if(s.equalsIgnoreCase(word)) {
                n++;
            }
        }

        return n / set.size();
    }

    private Double calculateIDF(List<Document> documents, String word) {
        double n = 0;
        for (Document document : documents) {
            List<String> list = document.getWords();
            for (String s : list) {
                if(s.equalsIgnoreCase(word)) {
                    n++;
                    break;
                }
            }
        }


        return Math.log(documents.size() / n);
    }

    private Double calculateTF_IDF_ForWord(List documents, List<String> words,  String word) {
        double tf = this.calculateTF(words, word);
        double idf = this.calculateIDF(documents, word);

        return tf * idf;
    }

    public void calculateTF_IDF_ForDocuments(List<Document> documents) {
        for (Document document : documents) {
            List<String> words = document.getWords();
            for (String s : words) {
                document.putTfIdfItem(s, this.calculateTF_IDF_ForWord(documents, words, s));
            }
        }

    }

}
