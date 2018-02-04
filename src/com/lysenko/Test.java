package com.lysenko;

import com.lysenko.entities.Document;
import com.lysenko.services.CollectionService;
import com.lysenko.utils.MapUtils;
import com.lysenko.services.TF_IDFService;

import java.io.IOException;
import java.util.*;

public class Test {

    public static void main(String[] args) throws IOException {
        // services
        CollectionService collectionService = new CollectionService();
        TF_IDFService tfIdfService = new TF_IDFService();
        MapUtils mapUtils = new MapUtils();

        // 1. Prepare documents collection: Create document with name, uuid, text and list of words
        List<Document> documents = collectionService.prepareDocumentsCollection();

        // 2. Calculate tf-idf for each document and save it in the documentsentity
        tfIdfService.calculateTF_IDF_ForDocuments(documents);

        // 3. Show documents with key words and their tf-idf indexes
        for(Document document : documents) {
            System.out.println(document.getName());
            Map<String, Double> words = document.getTfIdfFilesMap();
            Map<String, Double> sorted = mapUtils.sort(words, "DESC");
            mapUtils.showN(sorted, 10);
            System.out.println();

        }

    }

}
