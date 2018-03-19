package tokenizers;

import exceptions.InvalidInputException;
import filters.FilterChain;
import models.RawInput;
import models.Token;
import models.Tokens;

public class AnswerTokenizer implements Tokenizer {

    private FilterChain filterChain;
    private static final String DELIMITER = ";";

    public AnswerTokenizer(FilterChain filterChain) {
        this.filterChain = filterChain;
    }

    @Override
    public Tokens generateTokens(RawInput input) throws InvalidInputException {
        if(input == null || input.getAnswers() == null)
            throw new InvalidInputException("No input data to tokenize");
        Tokens tokens = new Tokens();
        for(String answer: input.getAnswers().split(DELIMITER)) {
            Token token = new Token(filterChain.filter(answer), answer.trim());
            tokens.addToken(token);
        }
        return tokens;
    }
}
