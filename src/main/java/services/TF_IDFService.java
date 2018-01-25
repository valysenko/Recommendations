package main.java.services;

import java.util.*;

public class TF_IDFService {

    private double calculateTF(Set<String> set, String word) {
        double n = 0;
        for (String s : set) {
            if(s.equalsIgnoreCase(word)) {
                n++;
                break;
            }
        }

        return n / set.size();
    }

    private double calculateIDF(HashMap<UUID, Set<String>> documents, String word) {
        double n = 0;
        for(Map.Entry<UUID, Set<String>> entry : documents.entrySet()) {
            Set<String> set = entry.getValue();
            for (String s : set) {
                if(s.equalsIgnoreCase(word)) {
                    n++;
                    break;
                }
            }
        }

        return Math.log(documents.size() / n);
    }

    private double calculateTF_IDF_ForWord(HashMap<UUID, Set<String>> documents, Set<String> words,  String word) {
        double tf = this.calculateTF(words, word);
        double idf = this.calculateIDF(documents, word);

        return tf * idf;
    }

    public HashMap<UUID, HashMap<String, Double>> calculateTF_IDF_ForCollection(HashMap<UUID, Set<String>> documents) {
        HashMap<UUID, HashMap<String, Double>> result = new HashMap<UUID, HashMap<String, Double>>();

        for(Map.Entry<UUID, Set<String>> entry : documents.entrySet()) {
            UUID documentUuid = entry.getKey();
            Set<String> words = entry.getValue();

            HashMap<String, Double> tfIdfDocumentMap = new HashMap<String, Double>();
            for (String s : words) {
                tfIdfDocumentMap.put(s, this.calculateTF_IDF_ForWord(documents, words, s));
            }

            result.put(documentUuid, tfIdfDocumentMap);
        }

        return result;
    }
}
