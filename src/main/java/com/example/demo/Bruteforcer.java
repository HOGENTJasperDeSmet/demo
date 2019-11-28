package com.example.demo;

import java.util.Arrays;

public class Bruteforcer {
    private static final String NUMERIC_ALPHABET = "0123456789";
    private static final String LOWER_CASE_ALPHABET = "abcdefghijklmnopqrstuvwxyz";
    private static final String UPPER_CASE_ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private final char[] alphabet;
    private final int alphabetLength;
    private int[] indices;
    private char[] combination;

    private Bruteforcer(String alphabet) {
        this.alphabet = alphabet.toCharArray();
        this.alphabetLength = alphabet.length();

        indices = new int[1];
        combination = new char[1];
    }

    public static Bruteforcer createNumericBruteForcer() {
        return new Bruteforcer(NUMERIC_ALPHABET);
    }

    public static Bruteforcer createAlphaBruteForcer() {
        return new Bruteforcer(LOWER_CASE_ALPHABET + UPPER_CASE_ALPHABET);
    }

    public static Bruteforcer createAlphaNumericBruteForcer() {
        return new Bruteforcer(LOWER_CASE_ALPHABET + UPPER_CASE_ALPHABET + NUMERIC_ALPHABET);
    }


    public static Bruteforcer createGenericBruteForcer(String alphabet) {
        return new Bruteforcer(alphabet);
    }

    public String computeNextCombination() {
        combination[0] = alphabet[indices[0]];
        String nextCombination = String.valueOf(combination);

        if (indices[0] < alphabetLength - 1) {
            indices[0]++;
        } else {
            for (int i = 0; i < indices.length; i++) {
                if (indices[i] < alphabetLength - 1) {
                    indices[i]++;
                    combination[i] = alphabet[indices[i]];
                    break;
                } else {
                    indices[i] = 0;
                    combination[i] = alphabet[indices[i]];

                    if (i == indices.length - 1) {
                        indices = Arrays.copyOf(indices, indices.length + 1);
                        combination = Arrays.copyOf(combination, combination.length + 1);
                        combination[combination.length - 1] = alphabet[indices[indices.length - 1]];
                        break;
                    }
                }
            }
        }

        return nextCombination;
    }
}

