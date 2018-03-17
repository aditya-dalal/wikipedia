package mappers;

import data.Tokens;
import exceptions.InvalidInputException;
import groups.UnitTests;
import models.Token;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.rules.ExpectedException;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import testData.TestData;

@Category(UnitTests.class)
public class LCSTokenMapperTest {

    private TokenMapper mapper;

    @Mock
    private LCS lcs;

    @Rule
    public final ExpectedException expectedException = ExpectedException.none();

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mapper = new LCSTokenMapper(lcs);
    }

    @Test
    public void testGetMatchingTokenForValidInput() throws InvalidInputException {
        Tokens tokens = TestData.getNewTokens();
        when(lcs.getLCSLength(anyString(), anyString())).thenReturn(31);
        Token token = mapper.getMatchingToken(tokens, "grévy'szebraandthemountainzebraareendangered");
        assertEquals("Grévy's zebra and the mountain zebra", token.getValue());
    }

    @Test
    public void testGetMatchingTokenWhenTokensIsNull() throws InvalidInputException {
        expectedException.expect(InvalidInputException.class);
        expectedException.expectMessage("Token and input cannot be null or empty");
        mapper.getMatchingToken(null, "dummy");
    }

    @Test
    public void testGetMatchingTokenWhenInputIsNull() throws InvalidInputException {
        expectedException.expect(InvalidInputException.class);
        expectedException.expectMessage("Token and input cannot be null or empty");
        mapper.getMatchingToken(TestData.getNewTokens(), null);
    }

    @Test
    public void testGetMatchingTokenWhenInputIsEmpty() throws InvalidInputException {
        expectedException.expect(InvalidInputException.class);
        expectedException.expectMessage("Token and input cannot be null or empty");
        mapper.getMatchingToken(TestData.getNewTokens(), "");
    }

    @Test
    public void testGetMatchingTokenWhenLCSLengthIsLess() throws InvalidInputException {
        Tokens tokens = TestData.getNewTokens();
        when(lcs.getLCSLength("theplainszebra,thegrévy'szebraandthemountainzebra", "grévy'szebraandthemountainzebraareendangered")).thenReturn(31);
        when(lcs.getLCSLength("grévy'szebraandthemountainzebra", "grévy'szebraandthemountainzebraareendangered")).thenReturn(30);
        Token token = mapper.getMatchingToken(tokens, "grévy'szebraandthemountainzebraareendangered");
        assertEquals("the plains zebra, the Grévy's zebra and the mountain zebra", token.getValue());
    }

    @Test
    public void testGetMatchingTokenWhenAccuracyIsLess() throws InvalidInputException {
        Tokens tokens = TestData.getTokensWithSameLengthButLessAccuracy();
        when(lcs.getLCSLength("grévy'szebraandthemountainzebra", "grévy'szebraandthemountainzebraareendangered")).thenReturn(31);
        when(lcs.getLCSLength("grévy'szebraandthemountainzebraare", "grévy'szebraandthemountainzebraareendangered")).thenReturn(31);
        Token token = mapper.getMatchingToken(tokens, "grévy'szebraandthemountainzebraareendangered");
        assertEquals("Grévy's zebra and the mountain zebra", token.getValue());
    }
}
