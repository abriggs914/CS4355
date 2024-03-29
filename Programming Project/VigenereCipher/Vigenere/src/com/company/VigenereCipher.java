package com.company;

/*
 CS4355 Cryptanalysis Final Project
 Question 2
 Vigenere Cipher
 Dec.2/19
 Avery Briggs
 3471065


 Java program to crack the Vigenere cypher
 on a the text file "vigenere.txt" which is an
 encrypted file using the hidden key.

*/

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.util.ArrayList;

public class VigenereCipher {
    private static String vigenere;
    private static String originalText;
    private static String decryptedText;
    private static String fileName;
    private static String estimateKey;
    private static int keyLength;
    private static int[] keyShiftValues;
    private static double ic;
    private static double[][] sampleICValues;
    private static double[] alphabetFrequencies;
    private static int[][] punctuation;

    public static void main(String[] args){
        fileName = "C:\\Users\\abrig\\Documents\\CS4355\\CS4355\\Programming Project\\VigenereCipher\\Vigenere\\src\\com\\company\\computerMessage.txt";
        if(!loadVigenere()){
            System.out.println("Error failed to load file.");
            System.exit(1);
        }
        format();
        initAlphabetFrequencies();
        initSampleICValues();
        getIC();
        lookUpIC();
        computeKeyShift();
        decryptVignere();
        printCipherStats();
    }

    private static void printCipherStats() {
        System.out.println("\tOriginal text entered via vigenere.txt:");
        System.out.println(originalText);
        System.out.println("\nComputed IC: " + ic);
        System.out.println("Estimated key length: " + keyLength);
        System.out.println("Estimated key: " + estimateKey + "\n");
        System.out.println("\tThe decrypted message is:\n" + decryptedText);
    }

    private static void format(){
        ArrayList<Integer> temp = new ArrayList<Integer>();
        StringBuilder res = new StringBuilder();
        for(int i = 0; i < vigenere.length(); i++){
            if(vigenere.charAt(i) > 'Z' || vigenere.charAt(i) < 'A'){
                temp.add(i);
            }
            else{
                res.append(vigenere.charAt(i));
            }
        }
        punctuation = new int[temp.size()][2];
        for(int i = 0; i < temp.size(); i++){
            punctuation[i][0] = temp.get(i);
            punctuation[i][1] = vigenere.charAt(temp.get(i));
        }
        originalText = vigenere;
        vigenere = res.toString();
//        System.out.println("originalText: " + originalText);
//        System.out.println("vigenere: " + vigenere);
//        for(int[] punc : punctuation) {
//            System.out.println("punc: " + Arrays.toString(punc));
//        }
    }

    private static void decryptVignere(){
        decryptedText = "";
        ArrayList<Integer> puncIndicies = new ArrayList<Integer>();
        for (int[] ints : punctuation) {
            puncIndicies.add(ints[0]);
        }
        for(int i = 0, y = 0, p = 0; i < originalText.length(); i++){
            System.out.print(originalText.charAt(i));
            if(puncIndicies.contains(i)){
                decryptedText += Character.toString((char) punctuation[p++][1]);
                puncIndicies.remove((Integer) i);
            }
//            else if(vigenere.charAt(y) > 'Z' || vigenere.charAt(y) < 'A'){
//                decryptedText += Character.toString(vigenere.charAt(y++));
//            }
            else{
                int x = i % keyLength;
                char c = (char) ((int) vigenere.charAt(y++) - keyShiftValues[x]);
                if(c < 'A'){
                    c += 26;
                }
                decryptedText += Character.toString(c);
            }
        }
    }

    private static void computeKeyShift(){
        keyShiftValues = new int[keyLength];
        int ksvIndex = 0;
        int len = vigenere.length();
        int numCharPerCol = (int) Math.ceil((double) len / (double) keyLength);
        int[][] temp = new int[keyLength][numCharPerCol];
        for(int i = 0; i < keyLength; i++){
            for(int j = i; j < len; j += keyLength){
                temp[i][j / keyLength] = vigenere.charAt(j);
            }
        }
        StringBuilder key = new StringBuilder();
        for(int i = 0; i < keyLength; i++){
            keyShiftValues[i] = computeShiftValue(temp[i]);
            char c = (char) (keyShiftValues[i] + 65);
            if(c > 'Z'){
                c -= 26;
            }
            key.append(c);
        }
        estimateKey = key.toString();
    }

    private static int computeShiftValue(int[] arr){
        double[][] letterCensus = new double[26][3];
        int len = arr.length;
        for(int i = 0, l = 'A'; i < 26; i++, l++){
            letterCensus[i][0] = l;
            int x = countOccurences(arr, l);
            letterCensus[i][1] = x;
            letterCensus[i][2] = (double) x / (double) len;
        }
        double[][] table = new double[len + 1][26];
        for(int i = 0; i < table.length; i++){
            for(int j = 0, offset = 0; j < table[i].length; j++, offset++){
                if(i == 0){
                    table[i][j] = alphabetFrequencies[j];
                }
                else{
                    int x = (j + i) % 26;
                    table[i][j] = letterCensus[x][2];
                }
            }
        }
        double[] products = new double[len];
        for(int i = 1; i < table.length; i++){
            double temp = 0;
            for(int j = 0; j < 26; j++){
                temp += table[0][j] * table[i][j];
            }
            products[i - 1] = temp;
        }
        return findMaxIndex(products) + 1;
    }

    private static int findMaxIndex(double[] arr){
        if(arr.length == 0){
            System.out.println("ERROR");
            return -1;
        }
        int maxIndex = 0;
        double maxSoFar = arr[0];
        for(int i = 1; i < arr.length; i++){
            // System.out.println("arr["+i+"]: " + arr[i]);
            if(arr[i] > maxSoFar){
                maxSoFar = arr[i];
                maxIndex = i;
            }
        }
        return maxIndex;
    }

    private static int countOccurences(int[] arr, int letter){
        int res = 0;
        for (int value : arr) {
            if (value == letter) {
                res++;
            }
        }
        return res;
    }

    private static void lookUpIC(){
        double min = Integer.MAX_VALUE;
        int index = 0;
        for(int i = 0; i < sampleICValues.length; i++){
            // System.out.println("sample["+i+"][1]: " + sampleICValues[i][1]);
            double diff = 0;
            diff = Math.abs(ic - sampleICValues[i][1]);
            // System.out.println("i: " + i + ", diff: " + diff + ", min: " + min + ", index: " + index);
            if(diff <= min){
                min = diff;
                index = i;
            }
        }
        keyLength = index + 1;
    }

    private static void initAlphabetFrequencies(){
        alphabetFrequencies = new double[26];
        alphabetFrequencies[0] = 0.082;  // A
        alphabetFrequencies[1] = 0.015;  // B
        alphabetFrequencies[2] = 0.028;  // C
        alphabetFrequencies[3] = 0.043;  // D
        alphabetFrequencies[4] = 0.127;  // E
        alphabetFrequencies[5] = 0.022;  // F
        alphabetFrequencies[6] = 0.020;  // G
        alphabetFrequencies[7] = 0.061;  // H
        alphabetFrequencies[8] = 0.070;  // I
        alphabetFrequencies[9] = 0.002;  // J
        alphabetFrequencies[10] = 0.008; // K
        alphabetFrequencies[11] = 0.040; // L
        alphabetFrequencies[12] = 0.024; // M
        alphabetFrequencies[13] = 0.067; // N
        alphabetFrequencies[14] = 0.075; // O
        alphabetFrequencies[15] = 0.019; // P
        alphabetFrequencies[16] = 0.001; // Q
        alphabetFrequencies[17] = 0.060; // R
        alphabetFrequencies[18] = 0.063; // S
        alphabetFrequencies[19] = 0.091; // T
        alphabetFrequencies[20] = 0.028; // U
        alphabetFrequencies[21] = 0.010; // V
        alphabetFrequencies[22] = 0.024; // W
        alphabetFrequencies[23] = 0.002; // X
        alphabetFrequencies[24] = 0.020; // Y
        alphabetFrequencies[25] = 0.001; // Z
        // showAlphabetFrequencies();
    }

    public static void showAlphabetFrequencies(){
        for (double alphabetFrequency : alphabetFrequencies) {
            System.out.print(alphabetFrequency + " ");
        }
    }

    private static void initSampleICValues(){
        sampleICValues = new double[5][2];
        for(int i = 0; i < 5; i++){
            sampleICValues[i][0] = (i + 1);
        }
        sampleICValues[0][1] = 0.066;
        sampleICValues[1][1] = 0.052;
        sampleICValues[2][1] = 0.047;
        sampleICValues[3][1] = 0.044;
        sampleICValues[4][1] = 0.043;
    }

    private static boolean loadVigenere(){
        boolean loaded = false;
        BufferedReader br;
        StringBuilder message = new StringBuilder();
        try{
            br = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
            while((vigenere = br.readLine()) != null){
                // System.out.println(vigenere);
                message.append(vigenere);
            }
            vigenere = message.toString();
            loaded = true;
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return loaded;
    }

    private static void getIC(){
        String line = vigenere.toUpperCase();
        double len = line.length();
        double temp = 0;
        for(int i = 'A'; i < 'Z'; i++){
            int ni = 0;
            for(int j = 0; j < len; j++){
                if(line.charAt(j) == i){
                    ni++;
                }
            }
            temp += ni * (ni - 1);
        }
        len = len * (len - 1);
        ic = temp / len;
        // System.out.println(temp);
        // System.out.println(len);
    }
}
