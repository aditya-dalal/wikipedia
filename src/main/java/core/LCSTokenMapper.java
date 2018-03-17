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
        int lcsLength = 0;
        for(Token token: tokens) {
            int len = lcs.getLCSLength(token, input);
            if (lcsLength < len) {
                lcsLength = len;
                result = token;
            }
        }
        return result;
    }
}
