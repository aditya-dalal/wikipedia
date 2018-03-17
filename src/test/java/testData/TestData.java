package testData;

import data.Tokens;
import models.RawInput;
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

    public static Tokens getTokensWithSameLengthButLessAccuracy() {
        Tokens tokens = new Tokens();
        tokens.addToken(new Token("grévy'szebraandthemountainzebra", "Grévy's zebra and the mountain zebra"));
        tokens.addToken(new Token("grévy'szebraandthemountainzebraare", "Grévy's zebra and the mountain zebra are"));
        return tokens;
    }

    public static RawInput getRawInput() {
        RawInput input = new RawInput();
        input.setAnswers("subgenus Hippotigris; horses and donkeys");
        input.setPassage("Unlike their closest relatives, horses and donkeys, zebras have never been truly domesticated. " +
                "The plains zebra and the mountain zebra belong to the subgenus Hippotigris, but Grévy's zebra " +
                "is the sole species of subgenus Dolichohippus.");
        input.addQuestion("Which animals are some of their closest relatives?");
        input.addQuestion("Which subgenus do the plains zebra and the mountain zebra belong to?");
        return input;
    }

    public static RawInput getRawInputWithAnswers() {
        RawInput input = new RawInput();
        input.setAnswers("subgenus Hippotigris");
        return input;
    }

    public static RawInput getRawInputWithPassage() {
        RawInput input = new RawInput();
        input.setPassage("Unlike their closest relatives, horses and donkeys, zebras have never been truly domesticated.");
        return input;
    }
}
