package com.lysenko;

import com.lysenko.entities.Document;
import com.lysenko.entities.TfIdfItem;
import com.lysenko.services.CollectionService;
import com.lysenko.services.UDKService;
import com.lysenko.utils.MapUtils;
import org.apache.pdfbox.pdmodel.PDDocument;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.io.FileUtils;
import org.apache.pdfbox.util.PDFTextStripper;

public class TestUDK {
    public static void main(String[] args) throws IOException {

        Document d = new Document();
        d.addTfIdfListItem(new TfIdfItem("one", 123.43));
        d.addTfIdfListItem(new TfIdfItem("two", 16.5));
        d.addTfIdfListItem(new TfIdfItem("three", 9.44));
        d.addTfIdfListItem(new TfIdfItem("four", 150.99));

        for(TfIdfItem item: d.getIfIdfList()) {
            System.out.println(item.getWord() + " " + item.getValue());
        }
        Collections.reverse(d.getIfIdfList());

        for(TfIdfItem item: d.getIfIdfList()) {
            System.out.println(item.getWord() + " " + item.getValue());
        }

//        File file = new File("http://www.ekmair.ukma.edu.ua/bitstream/handle/123456789/7951/Yeryemyeyev_Osnovni_rysy_liberal%27noyi.pdf");
        //URL url = new URL("http://www.ekmair.ukma.edu.ua/bitstream/handle/123456789/7952/Novichkova_Formuvannya_metodolohiyi_krytychnoho_analizu.pdf");
//        PDDocument pddDocument = PDDocument.load(new URL("http://www.ekmair.ukma.edu.ua/bitstream/handle/123456789/7951/Yeryemyeyev_Osnovni_rysy_liberal%27noyi.pdf"));
//        PDFTextStripper pdfStripper = new PDFTextStripper();
//        String text = pdfStripper.getText(pddDocument);
//        pddDocument.close();
//        System.out.println(text);
        //        pddDocument.
        // PDDocument pddDocument = PDDocument.
//  File f = FileUtils.toFile(url);
//        System.out.println(f.getName());
        //        File file = new File("http://www.ekmair.ukma.edu.ua/bitstream/handle/123456789/7952/Novichkova_Formuvannya_metodolohiyi_krytychnoho_analizu.pdf");

      //  PDDocument pdfDocument = PDDocument.load(f);

//        UDKService udkService = new UDKService();
//        MapUtils mapUtils = new MapUtils();
//        udkService.prepareUDKCollection();
//        Map<String, Integer> collection = mapUtils.getSortedMap(udkService.prepareUDKCollection(), "DESC");
//        for (Map.Entry<String, Integer> entry : collection.entrySet()) {
//            System.out.println(entry.getKey() + "   =   " + entry.getValue());
//        }
    }
}
