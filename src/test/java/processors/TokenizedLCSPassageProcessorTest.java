package processors;

import data.Tokens;
import exceptions.InvalidInputException;
import filters.FilterChain;
import groups.UnitTests;
import mappers.TokenMapper;
import models.Passage;
import models.Token;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.rules.ExpectedException;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import testData.TestData;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@Category(UnitTests.class)
public class TokenizedLCSPassageProcessorTest {

    private PassageProcessor passageProcessor;

    @Mock
    private Passage passage;
    @Mock
    private FilterChain filterChain;
    @Mock
    private TokenMapper mapper;
    @Mock
    private Tokens passageTokens;
    @Mock
    private Tokens answersTokens;
    @Rule
    public final ExpectedException expectedException = ExpectedException.none();

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        passageProcessor = new TokenizedLCSPassageProcessor(passage);
        when(passage.getFilterChain()).thenReturn(filterChain);
        when(passage.getMapper()).thenReturn(mapper);
        when(passage.getPassageTokens()).thenReturn(passageTokens);
        when(passage.getAnswerTokens()).thenReturn(answersTokens);
    }

    @Test
    public void testGetAnswerToQuestionForValidInput() throws InvalidInputException {
        Token token = TestData.getNewToken();
        when(filterChain.filter(anyString())).thenReturn("abc");
        when(mapper.getMatchingToken(passageTokens, "abc")).thenReturn(token);
        when(mapper.getMatchingToken(answersTokens, token.getToken())).thenReturn(token);
        String result = passageProcessor.getAnswerToQuestion("abc");
        assertEquals("Which Zebras are endangered? ", result);
    }

    @Test
    public void testGetAnswerToQuestionForNullInput() throws InvalidInputException {
        expectedException.expect(InvalidInputException.class);
        expectedException.expectMessage("Question cannot be null or empty");
        passageProcessor.getAnswerToQuestion(null);
    }

    @Test
    public void testGetAnswerToQuestionForEmptyInput() throws InvalidInputException {
        expectedException.expect(InvalidInputException.class);
        expectedException.expectMessage("Question cannot be null or empty");
        passageProcessor.getAnswerToQuestion("");
    }

    @Test
    public void testGetAnswerToQuestionWhenPassageTokenIsNull() throws InvalidInputException {
        when(filterChain.filter(anyString())).thenReturn("abc");
        when(mapper.getMatchingToken(passageTokens, "abc")).thenReturn(null);
        assertNull(passageProcessor.getAnswerToQuestion("abc"));
    }

    @Test
    public void testGetAnswerToQuestionWhenAnswerTokenIsNull() throws InvalidInputException {
        Token token = TestData.getNewToken();
        when(filterChain.filter(anyString())).thenReturn("abc");
        when(mapper.getMatchingToken(passageTokens, "abc")).thenReturn(token);
        when(mapper.getMatchingToken(answersTokens, token.getToken())).thenReturn(null);
        assertNull(passageProcessor.getAnswerToQuestion("abc"));
    }
}
