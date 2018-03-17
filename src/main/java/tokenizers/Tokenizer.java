package tokenizers;

import data.Tokens;

public interface Tokenizer {
    Tokens generateTokens(String input);
}
