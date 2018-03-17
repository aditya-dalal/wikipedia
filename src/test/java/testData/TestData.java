package testData;

import data.Tokens;
import models.Token;

public class TestData {

    public static Token getNewToken() {
        return new Token("whichzebrasareendangered?", "Which Zebras are endangered? ");
    }

    public static Tokens getNewTokens() {
        Tokens tokens = new Tokens();
        tokens.addToken(new Token("theplainszebra,thegrévy'szebraandthemountainzebra", "the plains zebra, the Grévy's zebra and the mountain zebra"));
        tokens.addToken(new Token("grévy'szebraandthemountainzebra", "Grévy's zebra and the mountain zebra"));
        return tokens;
    }

    public static Tokens geTokensWithSameLengthButLessAccuracy() {
        Tokens tokens = new Tokens();
        tokens.addToken(new Token("grévy'szebraandthemountainzebra", "Grévy's zebra and the mountain zebra"));
        tokens.addToken(new Token("grévy'szebraandthemountainzebraare", "Grévy's zebra and the mountain zebra are"));
        return tokens;
    }
}
