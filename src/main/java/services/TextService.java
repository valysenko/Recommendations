package main.java.services;

import java.util.*;

public class TextService {

    /**
     * @param word
     * @return String
     */
    private String normalizeString(String word) {
        return word.replaceAll("[!?+.^:,;='\"»«]","").toLowerCase();
    }

    /**
     *
     * @param text
     * @return Set<String>
     */
    public Set<String> breakTextIntoTokens(String text) {
        // LinkedHashSet
        Set<String> uniqueWords = new HashSet<String>();
        StringTokenizer st = new StringTokenizer(text);
        while (st.hasMoreTokens()) {
            String word = this.normalizeString(st.nextToken());
            uniqueWords.add(word);
        }

        return uniqueWords;
    }

}
