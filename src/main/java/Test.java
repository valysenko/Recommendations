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
        HashMap<UUID, Set<String>> filesMap = collectionService.buildFilesCollection();

        HashMap<UUID, HashMap<String, Double>> tfIdfFilesMap = tfIdfService.calculateTF_IDF_ForCollection(filesMap);

//        for(Map.Entry<UUID, Set<String>> entry : filesMap.entrySet()) {
//            UUID key = entry.getKey();
//            Set<String> set = entry.getValue();
//            Iterator iter = set.iterator();
//            while (iter.hasNext()) {
//                System.out.println(iter.next());
//            }
//        }
    }

}
