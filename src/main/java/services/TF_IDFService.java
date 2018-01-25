package main.java.services;

import java.util.*;

public class TF_IDFService {

    private Double calculateTF(List<String> set, String word) {
        double n = 0;
        for (String s : set) {
            if(s.equalsIgnoreCase(word)) {
                n++;
            }
        }

        //System.out.println("tf=" + n + " " + set.size() + "   " +  n / set.size());
        return n / set.size();
    }

    private Double calculateIDF(HashMap<UUID, List<String>> documents, String word) {
        double n = 0;
        for(Map.Entry<UUID, List<String>> entry : documents.entrySet()) {
            List<String> list = entry.getValue();
            for (String s : list) {
                if(s.equalsIgnoreCase(word)) {
                    n++;
                    break;
                }
            }
        }

        //System.out.println("idf=" + Math.log(documents.size() / n));
        return Math.log(documents.size() / n);
    }

    private Double calculateTF_IDF_ForWord(HashMap<UUID, List<String>> documents, List<String> words,  String word) {
        double tf = this.calculateTF(words, word);
        double idf = this.calculateIDF(documents, word);

        return tf * idf;
    }

    public HashMap<UUID, HashMap<String, Double>> calculateTF_IDF_ForCollection(HashMap<UUID, List<String>> documents) {
        HashMap<UUID, HashMap<String, Double>> result = new HashMap<UUID, HashMap<String, Double>>();

        for(Map.Entry<UUID, List<String>> entry : documents.entrySet()) {
            UUID documentUuid = entry.getKey();
            List<String> words = entry.getValue();

            HashMap<String, Double> tfIdfDocumentMap = new HashMap<String, Double>();
            for (String s : words) {
                tfIdfDocumentMap.put(s, this.calculateTF_IDF_ForWord(documents, words, s));
            }

            result.put(documentUuid, tfIdfDocumentMap);
        }

        return result;
    }
}
