package com.lysenko.services;

import com.lysenko.entities.Document;
import org.apache.commons.lang3.StringUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class UDKService {

    private TextService textService;

    public UDKService() {
        this.textService = new TextService();
    }

    /**
     * @param document
     * @return String
     * @throws IOException
     */
    private String getText(PDDocument document) throws IOException {
        PDFTextStripper pdfStripper = new PDFTextStripper();
        String text = pdfStripper.getText(document);
        document.close();

        return text;
    }

    private String getUDK() {

        return "asd";
    }

    /**
     * Method ...
     * @return HashMap<UUID, List<String>>
     * @throws IOException
     */
    public Map<String, Integer> prepareUDKCollection() throws IOException {
        HashMap<String, Integer> udkMap = new HashMap<String, Integer>();

        File dir = new File("documents/collection");
        File[] files = dir.listFiles();
        int total = 0;
        List<String> lines = new ArrayList<>();
        for (File child : files) {
            String fileName = child.getName();
            File file = new File("documents/collection/" + fileName);
            PDDocument pdfDocument = PDDocument.load(file);
            String text = this.getText(pdfDocument);
            String temp = "";
            if(StringUtils.containsIgnoreCase(text, "удк ")) {
                total++;
//                temp += fileName += ":  ";
                Scanner in = new Scanner(text);
                while(in.hasNext())
                {
                    String line=in.nextLine();
                    if(StringUtils.containsIgnoreCase(line, "удк ")) {
                        String key = StringUtils.trim(line);
                        if(udkMap.containsKey(key)) {
                            int value = udkMap.get(key);
                            udkMap.replace(key, udkMap.get(key), value+1);
                        } else {
                           udkMap.put(key,1);
                        }
//                        temp += line += "    ";
                    }
                }
                lines.add(temp);
            }
        }

//        lines.add("Total: " + total);
//        Path f = Paths.get("ukd.txt");
//        Files.write(f, lines, Charset.forName("UTF-8"));
//
//        System.out.println(total);
        return udkMap;
    }

}
