package core;

import data.Tokens;
import models.Token;

public class PassageTokenizer implements Tokenizer {

    private FilterChain filterChain;
    private static final String DELIMITER = "\\.";

    public PassageTokenizer(FilterChain filterChain) {
        this.filterChain = filterChain;
    }

    @Override
    public Tokens generateTokens(String input) {
        Tokens tokens = new Tokens();
        for(String sentence: input.split(DELIMITER)) {
            Token token = new Token(filterChain.filter(sentence), sentence.trim());
            tokens.addToken(token);
        }
        return tokens;
    }
}