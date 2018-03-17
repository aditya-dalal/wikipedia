package tokenizers;

import data.Tokens;
import exceptions.InvalidInputException;

public interface Tokenizer {
    Tokens generateTokens(String input) throws InvalidInputException;
}
