package com.prismx.model;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class lexicalAction {
    private BufferedReader br;
    HashMap<Integer, String> lineDict;
    HashMap<Integer, String> lexicalDict;

    public lexicalAction(File file) throws FileNotFoundException {
        this.br = new BufferedReader(new FileReader(file));
        this.lineDict = new HashMap<>();
        this.lexicalDict = new HashMap<>();
    }

    public void lexicalAnalysis() throws IOException {
        cleaner();
        for(HashMap.Entry<Integer, String> entry : lineDict.entrySet()) {
            ArrayList<String> lexemes=lexemeSplit(entry.getValue());
            int key=entry.getKey();
            StringBuilder toLexicalDict=new StringBuilder();
            for (String lexeme : lexemes) {
                if (!lexeme.isEmpty()) {
                    toLexicalDict.append(tokenizer(lexeme)+" ");
                    //System.out.print(tokenizer(lexeme)+" ");//debugging
                    //System.out.print(" " + lexeme + "\n");//for debugging
                }
            }
            lexicalDict.put(key, toLexicalDict.toString());
            toLexicalDict.setLength(0);

        }

        for(HashMap.Entry<Integer, String> entry : lexicalDict.entrySet()) {
            System.out.println("["+entry.getKey()+"] "+entry.getValue());
        }//Debugging
    }

    public HashMap<Integer, String> getLexicalDict() {
        return lexicalDict;
    }

    public void cleaner() throws IOException {
        String line;
        for(int i=1;(line=br.readLine())!=null;i++){
            if(!line.isEmpty()){
                lineDict.put(i,line);
                System.out.println(lineDict.get(i));//debugging
            }
        }
    }

    public boolean lexicalSuccessStatus(){
        if(lexicalDict.isEmpty()){
            return false;
        }
        return true;
    }

    public static String tokenizer(String lexeme){
        if(lexeme.matches("byte|short|int|long|double|float|char|String|boolean")){
            return "<data_type>";
        }
        else if(lexeme.equals("=")){
            return "<assignment_operator>";
        }
        else if(lexeme.equals(";")){
            return "<delimiter>";
        }
        else if (lexeme.matches("\".*\"|'[^']'|true|false|[-+]?\\d*\\.?\\d+([eE][-+]?\\d+)?[fFdDlL]?")) {
            return "<value>";
        }
        else if(lexeme.matches("[a-zA-Z_][a-zA-Z0-9_]*")){
            return "<identifier>";
        }
        return "<error>";
    }

    public static ArrayList<String> lexemeSplit(String input) {
        ArrayList<String> lexemes = new ArrayList<>();
        StringBuilder currentLexeme = new StringBuilder();
        boolean inString = false;
        char stringDelimiter = '"';

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (inString) {
                currentLexeme.append(c);
                if (c == stringDelimiter) {
                    inString = false;
                    lexemes.add(currentLexeme.toString().trim());
                    currentLexeme.setLength(0);
                }
            } else {
                if (c == '"' || c == '\'') {
                    if (currentLexeme.length() > 0) {
                        lexemes.add(currentLexeme.toString().trim());
                        currentLexeme.setLength(0);
                    }
                    inString = true;
                    stringDelimiter = c;
                    currentLexeme.append(c);
                } else if (c == '=' || c == ';') {
                    if (currentLexeme.length() > 0) {
                        lexemes.add(currentLexeme.toString().trim());
                        currentLexeme.setLength(0);
                    }
                    lexemes.add(String.valueOf(c));
                } else if (Character.isWhitespace(c)) {
                    if (currentLexeme.length() > 0) {
                        lexemes.add(currentLexeme.toString().trim());
                        currentLexeme.setLength(0);
                    }
                } else {
                    currentLexeme.append(c);
                }
            }
        }

        if (currentLexeme.length() > 0) {
            lexemes.add(currentLexeme.toString().trim());
        }
        return lexemes;
    }

}
