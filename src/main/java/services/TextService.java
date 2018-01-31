package main.java.services;

import java.util.*;

public class TextService {

    /**
     * @param word
     * @return String
     */
    private  String normalizeString(String word) {
        return word.toLowerCase().replaceAll("[^a-zа-яіїє']+","");
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
            String word = this.normalizeString(st.nextToken());
            words.add(word);
        }

        return words;
    }

}
