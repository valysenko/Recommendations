package main.java;

import com.sun.xml.internal.ws.util.StringUtils;
import main.java.services.CollectionService;
import main.java.services.TF_IDFService;
import main.java.services.TextService;

import javax.xml.soap.Text;
import java.io.IOException;
import java.io.StreamTokenizer;
import java.util.*;

public class Test {

    public static void main(String[] args) throws IOException {
        CollectionService collectionService = new CollectionService();
        TF_IDFService tfIdfService = new TF_IDFService();

        HashMap<UUID, List<String>> filesMap = collectionService.buildFilesCollection();
        HashMap<UUID, HashMap<String, Double>> tfIdfFilesMap = tfIdfService.calculateTF_IDF_ForCollection(filesMap);

        for(Map.Entry<UUID, HashMap<String, Double>> entry : tfIdfFilesMap.entrySet()) {
            UUID key = entry.getKey();
            System.out.println(key);
            HashMap<String, Double> words = entry.getValue();

            int i=0;
            for(Map.Entry<String, Double> innerEntry : words.entrySet()) {
                String word = innerEntry.getKey();
                Double tfIdfValue = innerEntry.getValue();
                System.out.println(word + " " + tfIdfValue);
                i++;
            }

            break;
        }
    }

}
