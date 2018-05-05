package com.lysenko;

import com.lysenko.entities.Document;
import com.lysenko.entities.DocumentCosineSimilarity;
import com.lysenko.services.CollectionService;
import com.lysenko.services.CosineSimilarityService;
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
        CosineSimilarityService cosineSimilarityService = new CosineSimilarityService();

        // 1. Prepare documents collection: Create document with name, uuid, text and list of words
        List<Document> documents = collectionService.prepareDocumentsCollection();

        // 2. Calculate tf-idf for each document and save it in the documentsentity
        tfIdfService.calculateTF_IDF_ForDocuments(documents);

        // 3. Save n sorted tf-idf collections
        for(Document document : documents) {
           Collections.sort(document.getIfIdfList());
        }



        // 4. Dictionary: use Redis
//        HashSet<String> dictionary = new HashSet<>();
//        for(Document document : documents) {
//            for (Map.Entry<String, Double> entry : document.getTfIdfSortedMap().entrySet()) {
//                dictionary.add(entry.getKey());
//            }
//        }

        // 5. Prepare cosine similarity collection: calculate cosine similarity for each 2 documents and save it
//        List<DocumentCosineSimilarity> dcsCollection = cosineSimilarityService.prepareCollection(documents, dictionary);
//
//        int i= 1;
//        for(DocumentCosineSimilarity dcs : dcsCollection) {
//            System.out.println(i + ". "+ dcs.getDocument1().getName() + " - " + dcs.getDocument2().getName());
//            System.out.println(dcs.getCosineSimilarityValue());
//            System.out.println();
//            i++;
//        }
//        System.out.println();
//        System.out.println();
//        System.out.println();
////
//        Collections.sort(dcsCollection, new Comparator<DocumentCosineSimilarity>() {
//            @Override
//            public int compare(DocumentCosineSimilarity lhs, DocumentCosineSimilarity rhs) {
//                // -1 - less than, 1 - greater than, 0 - equal, all inversed for descending
//                return lhs.getCosineSimilarityValue() > rhs.getCosineSimilarityValue() ? -1 :
//                        (lhs.getCosineSimilarityValue() < rhs.getCosineSimilarityValue()) ? 1 : 0;
//            }
//        });
//
//        // 6. Show
//        i= 1;
//        for(DocumentCosineSimilarity dcs : dcsCollection) {
//            System.out.println(i + ". "+ dcs.getDocument1().getName() + " - " + dcs.getDocument2().getName());
//            System.out.println(dcs.getCosineSimilarityValue());
//            System.out.println();
//            i++;
//        }

    }

}
