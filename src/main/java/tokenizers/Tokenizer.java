package tokenizers;

import data.Tokens;
import exceptions.InvalidInputException;
import models.RawInput;

public interface Tokenizer {
    Tokens generateTokens(RawInput input) throws InvalidInputException;
}
