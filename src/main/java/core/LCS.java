package core;

import models.Token;

/**
 * Created by aditya.dalal on 17/03/18.
 */
public class LCS {

    public int getLCSLength(Token token, String input) {
        int len = 0;
        if(token == null || input == null || input.isEmpty())
            return len;

        int lenSentence = token.getToken().length();
        int lenQuestion = input.length();
        int[][] longestSuffixMatrix = new int[lenSentence][lenQuestion];

        for(int i = 0; i < lenSentence; i++) {
            for (int j = 0; j < lenQuestion; j++) {
                if(token.getToken().charAt(i) == input.charAt(j)) {
                    if(i == 0 || j == 0)
                        longestSuffixMatrix[i][j] = 1;
                    else
                        longestSuffixMatrix[i][j] = 1 + longestSuffixMatrix[i-1][j-1];
                    if(longestSuffixMatrix[i][j] > len) {
                        len = longestSuffixMatrix[i][j];
                    }
                }
                else
                    longestSuffixMatrix[i][j] = 0;
            }
        }
        return len;
    }
}
