package main.java;

import com.sun.xml.internal.ws.util.StringUtils;
import main.java.services.CollectionService;

import java.io.IOException;
import java.io.StreamTokenizer;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.UUID;

public class Test {

    public static void main(String[] args) throws IOException {
        StringTokenizer stt = new StringTokenizer("this is 10 a test");
        while (stt.hasMoreTokens()) {

            System.out.println(stt.nextToken());
        }

        CollectionService collectionService = new CollectionService();

        HashMap<UUID, String> filesMap = collectionService.buildFilesCollection();

        for(Map.Entry<UUID, String> entry : filesMap.entrySet()) {
            UUID key = entry.getKey();
            String text = entry.getValue();
            StringTokenizer st = new StringTokenizer(text);

            // https://docs.oracle.com/javase/8/docs/api/java/util/StringTokenizer.html
            while (st.hasMoreTokens()) {
                System.out.println(st.nextToken());
            }

            break;
//            System.out.println(key);
//            System.out.println(text);
        }
    }

}
