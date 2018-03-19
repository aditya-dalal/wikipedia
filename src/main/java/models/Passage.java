package models;

import filters.FilterChain;
import lombok.Getter;
import mappers.TokenMapper;

@Getter
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
}
