package mappers;

import exceptions.InvalidInputException;
import models.Token;
import models.Tokens;

public class LCSTokenMapper implements TokenMapper {

    private LCS lcs;

    public LCSTokenMapper(LCS lcs) {
        this.lcs = lcs;
    }

    @Override
    public Token getMatchingToken(Tokens tokens, String input) throws InvalidInputException {
        Token result = null;
        if(tokens == null || input == null || input.isEmpty())
            throw new InvalidInputException("Token and input cannot be null or empty");
        double accuracy = 0.0;
        int len = 0;
        for(Token token: tokens) {
            int currentLen = lcs.getLCSLength(token.getToken(), input);
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
