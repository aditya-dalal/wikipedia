package mappers;

public class LCS {

    public int getLCSLength(String token, String input) {
        int len = 0;
        if(token == null || token.isEmpty() || input == null || input.isEmpty())
            return len;

        int lenSentence = token.length();
        int lenInput = input.length();
        int[][] longestSuffixMatrix = new int[lenSentence][lenInput];

        for(int i = 0; i < lenSentence; i++) {
            for (int j = 0; j < lenInput; j++) {
                if(token.charAt(i) == input.charAt(j)) {
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
