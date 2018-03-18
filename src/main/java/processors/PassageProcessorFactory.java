package processors;

import data.Tokens;
import exceptions.InvalidInputException;
import filters.FilterChain;
import filters.FilterChainFactory;
import filters.FilterType;
import mappers.LCS;
import mappers.LCSTokenMapper;
import mappers.TokenMapper;
import models.Passage;
import models.RawInput;
import tokenizers.AnswerTokenizer;
import tokenizers.PassageTokenizer;
import tokenizers.Tokenizer;

public class PassageProcessorFactory {

    public static PassageProcessor getPassageProcessor(ProcessorType processorType, FilterType filterType,
                                                       RawInput input) throws InvalidInputException {
        if(processorType == null)
            throw new InvalidInputException("Processor type cannot be null");
        if(filterType == null)
            throw new InvalidInputException("Filter type cannot be null");
        if(input == null)
            throw new InvalidInputException("Input cannot be null");
        switch (processorType) {
            case TOKENIZED_LCS_PROCESSOR:
                FilterChain filterChain = FilterChainFactory.getFilterChain(filterType);
                return getTokenizedLCSPassageProcessor(input, filterChain);
            default:
                throw new InvalidInputException("Unknown processor type: " + processorType.toString());
        }
    }

    private static PassageProcessor getTokenizedLCSPassageProcessor(RawInput input, FilterChain filterChain) throws InvalidInputException {
        Tokens passageTokens = getPassageTokens(input, filterChain);
        Tokens answerTokens = getAnswerTokens(input, filterChain);
        TokenMapper mapper = new LCSTokenMapper(new LCS());
        Passage passage = new Passage(passageTokens, answerTokens, filterChain, mapper);
        return new TokenizedLCSPassageProcessor(passage);
    }

    private static Tokens getAnswerTokens(RawInput input, FilterChain filterChain) throws InvalidInputException {
        Tokenizer tokenizer = new AnswerTokenizer(filterChain);
        return tokenizer.generateTokens(input);
    }

    private static Tokens getPassageTokens(RawInput input, FilterChain filterChain) throws InvalidInputException {
        Tokenizer tokenizer = new PassageTokenizer(filterChain);
        return tokenizer.generateTokens(input);
    }

}
