package com.lysenko.services;

import com.lysenko.entities.Document;
import com.lysenko.entities.DocumentCosineSimilarity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class CosineSimilarityService {

    public double calculate(Document d1, Document d2, HashSet<String> dictionary) {
        double numerator = 0;
        double d1denominator = 0;
        double d2denominator = 0;
        for (String s : dictionary) {
            Double v1 = d1.getTfIdfSortedMap().get(s);
            Double v2 = d2.getTfIdfSortedMap().get(s);
            if (v1!=null && v2!=null) {
                numerator += v1*v2;
            }

            if (v1!=null) {
                d1denominator += v1*v1;
            }
            if (v2!=null) {
                d2denominator += v2*v2;
            }

        }
        return numerator / (Math.sqrt(d1denominator) * Math.sqrt(d2denominator));
    }

    public DocumentCosineSimilarity prepareDocumentCosineSimilarity(Document d1, Document d2, HashSet<String> dictionary) {
        DocumentCosineSimilarity dcs = new DocumentCosineSimilarity();
        dcs.setCosineSimilarityValue(this.calculate(d1, d2, dictionary));
        dcs.setDocument1(d1);
        dcs.setDocument2(d2);

        return dcs;
    }

    public List<DocumentCosineSimilarity> prepareCollection(List<Document> documents, HashSet<String> dictionary) {
        List<DocumentCosineSimilarity> list = new ArrayList<>();
        for(int i = 0; i < documents.size(); i++) {
            for(int j = i+1; j< documents.size(); j++) {
                list.add(this.prepareDocumentCosineSimilarity(documents.get(i), documents.get(j), dictionary));
            }

        }
        return list;
    }
}
