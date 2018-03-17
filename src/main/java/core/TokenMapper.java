package core;

import data.Tokens;
import models.Token;

public interface TokenMapper {
    Token getMatchingToken(Tokens tokens, String input);
}
