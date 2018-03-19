package tokenizers;

import exceptions.InvalidInputException;
import filters.FilterChain;
import groups.UnitTests;
import models.RawInput;
import models.Token;
import models.Tokens;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.rules.ExpectedException;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import testData.TestData;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@Category(UnitTests.class)
public class AnswerTokenizerTest {

    private Tokenizer tokenizer;

    @Mock
    private FilterChain filterChain;

    @Rule
    public final ExpectedException expectedException = ExpectedException.none();

    @Before
    public void setup() throws InvalidInputException {
        MockitoAnnotations.initMocks(this);
        tokenizer = new AnswerTokenizer(filterChain);
        when(filterChain.filter("subgenus Hippotigris")).thenReturn("subgenushippotigris");
    }

    @Test
    public void testGenerateTokensForValidInput() throws InvalidInputException {
        Tokens tokens = tokenizer.generateTokens(TestData.getRawInputWithAnswers());
        for(Token token: tokens) {
            assertEquals("subgenushippotigris", token.getToken());
            assertEquals("subgenus Hippotigris", token.getValue());
        }
    }

    @Test
    public void testGenerateTokensForNullInput() throws InvalidInputException {
        expectedException.expect(InvalidInputException.class);
        expectedException.expectMessage("No input data to tokenize");
        tokenizer.generateTokens(null);
    }

    @Test
    public void testGenerateTokensForNullAnswersInInput() throws InvalidInputException {
        RawInput input = new RawInput();
        expectedException.expect(InvalidInputException.class);
        expectedException.expectMessage("No input data to tokenize");
        tokenizer.generateTokens(input);
    }
}
