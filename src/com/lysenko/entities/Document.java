package com.lysenko.entities;

import java.util.*;

public class Document {

    private UUID uuid;
    private String name;
    private String text;
    private List<String> words;
    private HashMap<String, Double> tfIdfMap;

    // for convenience in testing
    private LinkedHashMap<String, Double> tfIdfSortedMap;

    public Document(UUID uuid, String name, String text, List<String> words) {
        this.uuid = uuid;
        this.name = name;
        this.text = text;
        this.words = words;
        this.tfIdfMap = new HashMap<>();
        this.tfIdfSortedMap = new LinkedHashMap<>();
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Map<String, Double> getTfIdfMap() {
        return tfIdfMap;
    }

    public void putTfIdfItem(String word, Double tfIdf) {
        this.tfIdfMap.put(word, tfIdf);
    }

    public LinkedHashMap<String, Double> getTfIdfSortedMap() {
        return this.tfIdfSortedMap;
    }

    public void putTfIdfSortedItem(String word, Double tfIdf) {
        this.tfIdfSortedMap.put(word, tfIdf);
    }

    public List<String> getWords() {
        return words;
    }

    public void addWord(String word) {
        this.words.add(word);
    }

}
