package com.lysenko.services;

import org.tartarus.snowball.SnowballProgram;
import org.tartarus.snowball.ext.Ukrainian;

import java.util.*;

public class TextService {

    private SnowballProgram stemmer;

    public TextService() {
        this.stemmer = new Ukrainian();
    }

    /**
     * @param word
     * @return String
     */
    private  String normalizeString(String word) {
        return word.toLowerCase().replaceAll("[^a-zа-яіїєґ']+","");
    }

    private String getStemmed(String word) {
        this.stemmer.setCurrent(word);
        this.stemmer.stem();

        return this.stemmer.getCurrent();
    }

    /**
     *
     * @param text
     * @return List<String>
     */
    public List<String> breakTextIntoTokens(String text) {
        // LinkedHashSet
        List<String> words = new ArrayList<String>();
        StringTokenizer st = new StringTokenizer(text);
        while (st.hasMoreTokens()) {
            String word = this.getStemmed(this.normalizeString(st.nextToken()));
            if (word.length() >= 3) {
                words.add(word);
            }
        }

        return words;
    }

    /**
     *
     * @param text
     * @return List<String>
     */
    public List<String> breakTextIntoTwoGrams(String text) {
        // LinkedHashSet
        List<String> words = new ArrayList<String>();
        StringTokenizer st = new StringTokenizer(text);

        String temp = this.getStemmed(this.normalizeString(st.nextToken()));
        while (st.hasMoreTokens()) {
            String word = this.getStemmed(this.normalizeString(st.nextToken()));
            if (word.length() >= 3 && temp.length() >= 3) {
                words.add(temp + " " + word);
            }
            temp = word;
        }

        return words;
    }

    /**
     *
     * @param text
     * @return List<String>
     */
    public List<String> breakTextIntoUniGramsAndTwoGrams(String text) {
        // LinkedHashSet
        List<String> words = new ArrayList<String>();
        StringTokenizer st = new StringTokenizer(text);

        String temp = this.getStemmed(this.normalizeString(st.nextToken()));
        while (st.hasMoreTokens()) {
            String word = this.getStemmed(this.normalizeString(st.nextToken()));
            if (temp.length() >= 3) {
                words.add(temp);
                if (word.length() >= 3) {
                    words.add(temp + " " + word);
                }
            }
            temp = word;
        }

        return words;
    }

}
