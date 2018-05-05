package com.lysenko.services;

import com.lysenko.entities.Document;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentInformation;
import org.apache.pdfbox.util.PDFTextStripper;

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
    public List<Document> prepareDocumentsCollection() throws IOException {
        List<Document> list = new ArrayList<>();
        File dir = new File("documents/100");
        File[] files = dir.listFiles();
        for (File child : files) {
            String fileName = child.getName();
            File file = new File("documents/100/" + fileName);
            PDDocument pdfDocument = PDDocument.load(file);
            String text = this.getText(pdfDocument);
            List<String> words = this.textService.breakTextIntoUniGramsAndBiGrams(text);
            Document document = new Document(UUID.randomUUID(), fileName, this.getText(pdfDocument), words);
            list.add(document);
        }

        return list;
    }

}
