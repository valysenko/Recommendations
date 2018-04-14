package com.lysenko.services;

import com.lysenko.entities.Document;
import com.lysenko.utils.MapUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.*;

public class TF_IDFService {

    private MapUtils mapUtils;

    public TF_IDFService() {
        this.mapUtils = new MapUtils();
    }

    private Double calculateTF(List<String> set, String word) {
        double n = Collections.frequency(set, word);

        return n / set.size();
    }

    private Double calculateIDF(List<Document> documents, String word) {
        double n = 0;
        for (Document document : documents) {
            List<String> list = document.getWords();
            if(list.contains(word)) {
                n++;
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
