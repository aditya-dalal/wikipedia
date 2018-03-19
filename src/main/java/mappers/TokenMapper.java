package mappers;

import exceptions.InvalidInputException;
import models.Token;
import models.Tokens;

public interface TokenMapper {
    Token getMatchingToken(Tokens tokens, String input) throws InvalidInputException;
}
