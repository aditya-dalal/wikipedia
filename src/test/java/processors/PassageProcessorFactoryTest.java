package processors;

import exceptions.InvalidInputException;
import filters.FilterType;
import groups.UnitTests;
import org.junit.Rule;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.rules.ExpectedException;
import testData.TestData;

import static org.junit.Assert.assertTrue;

@Category(UnitTests.class)
public class PassageProcessorFactoryTest {

    @Rule
    public final ExpectedException expectedException = ExpectedException.none();

    @Test
    public void testGetPassageProcessorForValidInput() throws InvalidInputException {
        PassageProcessor processor = PassageProcessorFactory.getPassageProcessor(ProcessorType.TOKENIZED_LCS_PROCESSOR, FilterType.ALL, TestData.getRawInput());
        assertTrue(processor instanceof TokenizedLCSPassageProcessor);
    }

    @Test
    public void testGetPassageProcessorWhenProcessorTypeIsNull() throws InvalidInputException {
        expectedException.expect(InvalidInputException.class);
        expectedException.expectMessage("Processor type cannot be null");
        PassageProcessorFactory.getPassageProcessor(null, FilterType.ALL, TestData.getRawInput());
    }

    @Test
    public void testGetPassageProcessorWhenFilterTypeIsNull() throws InvalidInputException {
        expectedException.expect(InvalidInputException.class);
        expectedException.expectMessage("Filter type cannot be null");
        PassageProcessorFactory.getPassageProcessor(ProcessorType.TOKENIZED_LCS_PROCESSOR, null, TestData.getRawInput());
    }

    @Test
    public void testGetPassageProcessorWhenInputIsNull() throws InvalidInputException {
        expectedException.expect(InvalidInputException.class);
        expectedException.expectMessage("Input cannot be null");
        PassageProcessorFactory.getPassageProcessor(ProcessorType.TOKENIZED_LCS_PROCESSOR, FilterType.ALL, null);
    }
}
