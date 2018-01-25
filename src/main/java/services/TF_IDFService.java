package main.java.services;

import java.util.*;

public class TF_IDFService {
    /**
     * @param doc  list of strings
     * @param term String represents a term
     * @return term frequency of term in document
     */
    public double tf2(List<String> doc, String term) {
        double result = 0;
        for (String word : doc) {
            if (term.equalsIgnoreCase(word))
                result++;
        }
        return result / doc.size();
    }

    /**
     * @param docs list of list of strings represents the dataset
     * @param term String represents a term
     * @return the inverse term frequency of term in documents
     */
    public double idf2(List<List<String>> docs, String term) {
        double n = 0;
        for (List<String> doc : docs) {
            for (String word : doc) {
                if (term.equalsIgnoreCase(word)) {
                    n++;
                    break;
                }
            }
        }
        return Math.log(docs.size() / n);
    }

    /**
     * @param doc  a text document
     * @param docs all documents
     * @param term term
     * @return the TF-IDF of term
     */
    public double tfIdf(List<String> doc, List<List<String>> docs, String term) {
        return tf2(doc, term) * idf2(docs, term);

    }

    public static void main(String[] args) {

        List<String> doc1 = Arrays.asList("Lorem", "ipsum", "dolor", "ipsum", "sit", "ipsum");
        List<String> doc2 = Arrays.asList("Vituperata", "incorrupte", "at", "ipsum", "pro", "quo");
        List<String> doc3 = Arrays.asList("Has", "persius", "disputationi", "id", "simul");
        List<List<String>> documents = Arrays.asList(doc1, doc2, doc3);

        TF_IDFService calculator = new TF_IDFService();
        double tfidf = calculator.tfIdf(doc1, documents, "ipsum");
        System.out.println("TF-IDF (ipsum) = " + tfidf);

        double tfidf2 = calculator.tfIdf(doc1, documents, "Lorem");
        System.out.println("TF-IDF (Lorem) = " + tfidf2);


    }

///////////
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

            result.put(documentUuid, tfIdfDocumentMap);        }

        return result;
    }
}
