package com.lysenko.services;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentInformation;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * For working with PDF files Apache pdfbox library is used
 */
public class CollectionService {


    private TextService textService;

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

    /**
     * @param document
     * @return String
     * @throws IOException
     */
    private String getTitle(PDDocument document) throws IOException {
        PDDocumentInformation info = document.getDocumentInformation();
        String title = info.getTitle();
        document.close();

        return title;
    }

    /**
     * @param document
     * @return String
     * @throws IOException
     */
    private String getAuthor(PDDocument document) throws IOException {
        PDDocumentInformation info = document.getDocumentInformation();
        String author = info.getAuthor();
        document.close();

        return author;
    }

    /**
     * @param document
     * @return String
     * @throws IOException
     */
    private String getKeyWords(PDDocument document) throws IOException {
        PDDocumentInformation info = document.getDocumentInformation();
        String keyWords = info.getKeywords();
        document.close();

        return keyWords;
    }


    private void showMetadata(PDDocument document) throws IOException {
        PDDocumentInformation info = document.getDocumentInformation();
        System.out.println("Page Count=" + document.getNumberOfPages());
        System.out.println("Title=" + info.getTitle());
        System.out.println("Author=" + info.getAuthor());
        System.out.println("Subject=" + info.getSubject());
        System.out.println("Keywords=" + info.getKeywords());
        System.out.println("Creator=" + info.getCreator());
        System.out.println("Producer=" + info.getProducer());
        System.out.println("Creation Date=" + info.getCreationDate());
        System.out.println("Modification Date=" + info.getModificationDate());
        System.out.println("Trapped=" + info.getTrapped());

        document.close();
    }

    public CollectionService() {
        this.textService = new TextService();
    }

    /**
     * Method ...
     * @return HashMap<UUID, List<String>>
     * @throws IOException
     */
    public HashMap<UUID, List<String>> buildFilesCollection() throws IOException {
        HashMap<UUID, List<String>> filesMap = new HashMap<UUID, List<String>>();

        File dir = new File("documents/2016");
        File[] files = dir.listFiles();

        if (files != null) {
            for (File child : files) {
                String fileName = child.getName();
                File file = new File("documents/2016/" + fileName);
                PDDocument document = PDDocument.load(file);
                List<String> words = this.textService.breakTextIntoTokens(this.getText(document));
                UUID uuid = UUID.randomUUID();
                System.out.println(fileName + " " + uuid);
                filesMap.put(uuid, words);
            }
        } else {
            //TODO
        }

        return filesMap;
    }

}