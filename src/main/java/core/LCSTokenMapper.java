package core;

import data.Tokens;
import models.Token;

public class LCSTokenMapper implements TokenMapper {

    private LCS lcs;

    public LCSTokenMapper(LCS lcs) {
        this.lcs = lcs;
    }

    @Override
    public Token getMatchingToken(Tokens tokens, String input) {
        Token result = null;
        double accuracy = 0.0;
        int len = 0;
        for(Token token: tokens) {
            int currentLen = lcs.getLCSLength(token, input);
            double currentAccuracy = currentLen / (double) token.getToken().length();
            if(len <= currentLen && accuracy < currentAccuracy) {
                len = currentLen;
                accuracy = currentAccuracy;
                result = token;
            }
        }
        return result;
    }
}
