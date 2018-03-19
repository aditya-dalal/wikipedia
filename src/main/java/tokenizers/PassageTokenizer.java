package tokenizers;

import exceptions.InvalidInputException;
import filters.FilterChain;
import models.RawInput;
import models.Token;
import models.Tokens;

public class PassageTokenizer implements Tokenizer {

    private FilterChain filterChain;
    private static final String DELIMITER = "\\.";

    public PassageTokenizer(FilterChain filterChain) {
        this.filterChain = filterChain;
    }

    @Override
    public Tokens generateTokens(RawInput input) throws InvalidInputException {
        if(input == null || input.getPassage() == null)
            throw new InvalidInputException("No input data to tokenize");
        Tokens tokens = new Tokens();
        for(String sentence: input.getPassage().split(DELIMITER)) {
            Token token = new Token(filterChain.filter(sentence), sentence.trim());
            tokens.addToken(token);
        }
        return tokens;
    }
}