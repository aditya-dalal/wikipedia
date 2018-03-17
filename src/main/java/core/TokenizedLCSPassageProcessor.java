package core;

import models.Passage;
import models.Token;

public class TokenizedLCSPassageProcessor implements PassageProcessor {
    private Passage passage;

    public TokenizedLCSPassageProcessor(Passage passage) {
        this.passage = passage;
    }

    @Override
    public String getAnswerToQuestion(String question) {
        if(question == null || question.isEmpty())
            return null;
        String filteredQuestion = passage.getFilterChain().filter(question);
        TokenMapper mapper = passage.getMapper();
        Token matchingPassageToken = mapper.getMatchingToken(passage.getPassageTokens(), filteredQuestion);
        Token matchingAnswerToken = mapper.getMatchingToken(passage.getAnswerTokens(), matchingPassageToken.getToken());
        return matchingAnswerToken.getValue();
    }
}
