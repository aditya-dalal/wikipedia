package data;

import groups.UnitTests;
import models.Token;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import testData.TestData;

import static org.junit.Assert.assertEquals;

@Category(UnitTests.class)
public class TokensTest {

    private Tokens tokens;

    @Before
    public void setup() {
        tokens = new Tokens();
    }

    @Test
    public void testAddToken() {
        Token token = TestData.getNewToken();
        tokens.addToken(token);
        for(Token t: tokens) {
            assertEquals(token.getToken(), t.getToken());
            assertEquals(token.getValue(), t.getValue());
        }
    }

}
