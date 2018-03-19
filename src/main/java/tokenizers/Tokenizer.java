package tokenizers;

import exceptions.InvalidInputException;
import models.RawInput;
import models.Tokens;

public interface Tokenizer {
    Tokens generateTokens(RawInput input) throws InvalidInputException;
}
