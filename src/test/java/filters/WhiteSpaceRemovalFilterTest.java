package filters;

import exceptions.InvalidInputException;
import groups.UnitTests;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.rules.ExpectedException;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@Category(UnitTests.class)
public class WhiteSpaceRemovalFilterTest {

    private FilterChain filterChain;

    @Mock
    private FilterChain nextFilterChain;

    @Rule
    public final ExpectedException expectedException = ExpectedException.none();

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        filterChain = new WhiteSpaceRemovalFilter();
    }

    @Test
    public void testFilterForValidInput() throws InvalidInputException {
        assertEquals("abcdef", filterChain.filter("\tabc\n def"));
    }

    @Test
    public void testFilterForEmptyInput() throws InvalidInputException {
        assertEquals("", filterChain.filter(""));
    }

    @Test
    public void testFilterForNullInput() throws InvalidInputException {
        expectedException.expect(InvalidInputException.class);
        expectedException.expectMessage("Input string is null");
        assertEquals(null, filterChain.filter(null));
    }

    @Test
    public void testFilterWithNextFilter() throws InvalidInputException {
        filterChain.setFilterChain(nextFilterChain);
        when(nextFilterChain.filter("abcdef")).thenReturn("abcdef");
        assertEquals("abcdef", filterChain.filter("\tabc\n def"));
    }
}
