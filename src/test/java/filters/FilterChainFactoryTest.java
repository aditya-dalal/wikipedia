package filters;

import exceptions.InvalidInputException;
import groups.UnitTests;
import org.junit.Rule;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@Category(UnitTests.class)
public class FilterChainFactoryTest {

    @Rule
    public final ExpectedException expectedException = ExpectedException.none();

    @Test
    public void testFactoryForAllFilters() throws InvalidInputException {
        FilterChain filterChain = FilterChainFactory.getFilterChain(FilterType.ALL);
        assertNotNull(filterChain);
    }

    @Test
    public void testFactoryForLowerCaseFilter() throws InvalidInputException {
        FilterChain filterChain = FilterChainFactory.getFilterChain(FilterType.LOWERCASE);
        assertTrue(filterChain instanceof LowerCaseFilter);
    }

    @Test
    public void testFactoryForWhiteSpaceRemovalFilter() throws InvalidInputException {
        FilterChain filterChain = FilterChainFactory.getFilterChain(FilterType.WHITESPACE_REMOVAL);
        assertTrue(filterChain instanceof WhiteSpaceRemovalFilter);
    }

    @Test
    public void testFactoryForNoneFilter() throws InvalidInputException {
        FilterChain filterChain = FilterChainFactory.getFilterChain(FilterType.NONE);
        assertTrue(filterChain instanceof NoneFilter);
    }

    @Test
    public void testFactoryForNullFilter() throws InvalidInputException {
        expectedException.expect(InvalidInputException.class);
        expectedException.expectMessage("Filter type cannot be null");
        FilterChainFactory.getFilterChain(null);
    }
}
