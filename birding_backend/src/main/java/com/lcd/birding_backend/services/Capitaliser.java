package com.lcd.birding_backend.services;

import java.util.ArrayList;

public class Capitaliser {
    public String capitaliseEachWord(String sentence) {
        String[] words = sentence.split(" ");
        ArrayList<String> capitalisedWordList = new ArrayList<>();
        for (String word: words) {
            word = word.substring(0,1).toUpperCase() + word.substring(1).toLowerCase();
            capitalisedWordList.add(word);
        }

        String capitalisedWords = capitalisedWordList.get(0);
        int i;
        if (capitalisedWordList.size() > 1) {
            for (i=1; i<capitalisedWordList.size(); i++) {
                capitalisedWords = capitalisedWords + " " + capitalisedWordList.get(i);
            }
        }

        String finalWords = capitalisedWords.trim();
        return finalWords;
    }
}
