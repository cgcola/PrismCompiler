package com.prismx.model;

import java.util.HashMap;

public class syntaxAction {
    private HashMap<Integer,String> processedLexical;
    private HashMap<Integer, String> errors;

    public syntaxAction(HashMap<Integer,String> processedLexical) {
        this.processedLexical = processedLexical;
        this.errors = new HashMap<>();
    }
}
