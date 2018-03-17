package tokenizers;

import data.Tokens;
import exceptions.InvalidInputException;
import filters.FilterChain;
import models.Token;

public class AnswerTokenizer implements Tokenizer {

    private FilterChain filterChain;
    private static final String DELIMITER = ";";

    public AnswerTokenizer(FilterChain filterChain) {
        this.filterChain = filterChain;
    }

    @Override
    public Tokens generateTokens(String input) throws InvalidInputException {
        Tokens tokens = new Tokens();
        for(String answer: input.split(DELIMITER)) {
            Token token = new Token(filterChain.filter(answer), answer.trim());
            tokens.addToken(token);
        }
        return tokens;
    }
}
