package processors;

import exceptions.InvalidInputException;
import mappers.TokenMapper;
import models.Passage;
import models.Token;

public class TokenizedLCSPassageProcessor implements PassageProcessor {
    private Passage passage;

    public TokenizedLCSPassageProcessor(Passage passage) {
        this.passage = passage;
    }

    @Override
    public String getAnswerToQuestion(String question) throws InvalidInputException {
        if(question == null || question.isEmpty())
            throw new InvalidInputException("Question cannot be null or empty");
        String filteredQuestion = passage.getFilterChain().filter(question);
        TokenMapper mapper = passage.getMapper();
        Token matchingPassageToken = mapper.getMatchingToken(passage.getPassageTokens(), filteredQuestion);
        Token matchingAnswerToken = mapper.getMatchingToken(passage.getAnswerTokens(), matchingPassageToken.getToken());
        return matchingAnswerToken.getValue();
    }
}
