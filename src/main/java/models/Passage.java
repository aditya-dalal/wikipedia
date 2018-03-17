package models;

import core.FilterChain;
import core.TokenMapper;
import data.Tokens;

public class Passage {

    private Tokens passageTokens;
    private Tokens answerTokens;
    private FilterChain filterChain;
    private TokenMapper mapper;

    public Passage(Tokens passageTokens, Tokens answerTokens, FilterChain filterChain, TokenMapper mapper) {
        this.passageTokens = passageTokens;
        this.filterChain = filterChain;
        this.mapper = mapper;
        this.answerTokens = answerTokens;
    }

    public TokenMapper getMapper() {
        return this.mapper;
    }

    public FilterChain getFilterChain() {
        return this.filterChain;
    }

    public Tokens getPassageTokens() {
        return this.passageTokens;
    }

    public Tokens getAnswerTokens() {
        return this.answerTokens;
    }
}
