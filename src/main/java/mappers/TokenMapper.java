package mappers;

import data.Tokens;
import exceptions.InvalidInputException;
import models.Token;

public interface TokenMapper {
    Token getMatchingToken(Tokens tokens, String input) throws InvalidInputException;
}
