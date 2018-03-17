package filters;

import exceptions.InvalidInputException;
import groups.UnitTests;
import org.junit.Rule;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@Category(UnitTests.class)
public class FilterChainFactoryTest {

    private FilterChain filterChain;

    @Rule
    public final ExpectedException expectedException = ExpectedException.none();

    @Test
    public void testFactoryForAllFilters() throws InvalidInputException {
        filterChain = FilterChainFactory.getFilterChain(FilterType.ALL);
        assertEquals("abcdef", filterChain.filter("\tAbC Def \n"));
    }

    @Test
    public void testFactoryForLowerCaseFilter() throws InvalidInputException {
        filterChain = FilterChainFactory.getFilterChain(FilterType.LOWERCASE);
        assertEquals("abc def", filterChain.filter("AbC Def"));
    }

    @Test
    public void testFactoryForWhiteSpaceRemovalFilter() throws InvalidInputException {
        filterChain = FilterChainFactory.getFilterChain(FilterType.WHITESPACE_REMOVAL);
        assertEquals("AbCDef", filterChain.filter("\nAbC Def\t"));
    }

    @Test
    public void testFactoryForNoneFilter() throws InvalidInputException {
        filterChain = FilterChainFactory.getFilterChain(FilterType.NONE);
        assertNull(filterChain);
    }

    @Test
    public void testFactoryForNullFilter() throws InvalidInputException {
        expectedException.expect(InvalidInputException.class);
        expectedException.expectMessage("Filter type cannot be null");
        filterChain = FilterChainFactory.getFilterChain(null);
    }
}
