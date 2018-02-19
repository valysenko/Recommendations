package com.lysenko.entities;

public class DocumentCosineSimilarity {

    private Document document1;
    private Document document2;
    private double cosineSimilarityValue;

    public Document getDocument1() {
        return document1;
    }

    public void setDocument1(Document document1) {
        this.document1 = document1;
    }

    public Document getDocument2() {
        return document2;
    }

    public void setDocument2(Document document2) {
        this.document2 = document2;
    }

    public double getCosineSimilarityValue() {
        return cosineSimilarityValue;
    }

    public void setCosineSimilarityValue(double cosineSimilarityValue) {
        this.cosineSimilarityValue = cosineSimilarityValue;
    }
}
