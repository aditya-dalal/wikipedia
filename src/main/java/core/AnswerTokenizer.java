package core;

import data.Tokens;
import models.Token;

/**
 * Created by aditya.dalal on 17/03/18.
 */
public class AnswerTokenizer implements Tokenizer {

    private FilterChain filterChain;
    private static final String DELIMITER = ";";

    public AnswerTokenizer(FilterChain filterChain) {
        this.filterChain = filterChain;
    }

    @Override
    public Tokens generateTokens(String input) {
        Tokens tokens = new Tokens();
        for(String answer: input.split(DELIMITER)) {
            Token token = new Token(filterChain.filter(answer), answer.trim());
            tokens.addToken(token);
        }
        return tokens;
    }
}
