package integration;

import exceptions.InvalidInputException;
import filters.FilterType;
import groups.IntegrationTests;
import models.RawInput;
import org.junit.Rule;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.rules.ExpectedException;
import processors.PassageProcessor;
import processors.PassageProcessorFactory;
import processors.ProcessorType;
import testData.TestData;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@Category(IntegrationTests.class)
public class TokenizedLCSPassageProcessorTest {

    @Rule
    public final ExpectedException expectedException = ExpectedException.none();

    @Test
    public void testGetAnswerToQuestionForValidInput() throws InvalidInputException, FileNotFoundException {
        InputStream inputStream = getClass().getResourceAsStream("/input.txt");
        RawInput input = getInputFrom(inputStream);
        PassageProcessor passageProcessor = PassageProcessorFactory.getPassageProcessor(ProcessorType.TOKENIZED_LCS_PROCESSOR, FilterType.ALL, input);

        StringBuilder result = new StringBuilder();
        for (String question: input.getQuestions())
            result.append(passageProcessor.getAnswerToQuestion(question));
        assertEquals(expectedOutput(), result.toString());
    }

    @Test
    public void testGetAnswerToQuestionWhenPassageIsNull() throws InvalidInputException {
        RawInput input = TestData.getRawInput();
        input.setPassage(null);
        expectedException.expect(InvalidInputException.class);
        expectedException.expectMessage("No input data to tokenize");
        PassageProcessorFactory.getPassageProcessor(ProcessorType.TOKENIZED_LCS_PROCESSOR, FilterType.ALL, input);
    }

    @Test
    public void testGetAnswerToQuestionWhenAnswersAreNull() throws InvalidInputException {
        RawInput input = TestData.getRawInput();
        input.setAnswers(null);
        expectedException.expect(InvalidInputException.class);
        expectedException.expectMessage("No input data to tokenize");
        PassageProcessorFactory.getPassageProcessor(ProcessorType.TOKENIZED_LCS_PROCESSOR, FilterType.ALL, input);
    }

    @Test
    public void testGetAnswerToQuestionWhenNoAnswersMatch() throws InvalidInputException {
        RawInput input = new RawInput();
        input.setPassage("abcd");
        input.addQuestion("abc");
        input.setAnswers("efgh");
        PassageProcessor passageProcessor = PassageProcessorFactory.getPassageProcessor(ProcessorType.TOKENIZED_LCS_PROCESSOR, FilterType.ALL, input);
        assertNull(passageProcessor.getAnswerToQuestion(input.getQuestions().get(0)));
    }

    @Test
    public void testGetAnswerToQuestionForInvalidQuestion() throws InvalidInputException {
        RawInput input = new RawInput();
        input.setPassage("abcd");
        input.addQuestion("efgh");
        input.setAnswers("ab");
        PassageProcessor passageProcessor = PassageProcessorFactory.getPassageProcessor(ProcessorType.TOKENIZED_LCS_PROCESSOR, FilterType.ALL, input);
        assertNull(passageProcessor.getAnswerToQuestion(input.getQuestions().get(0)));
    }

    private String expectedOutput() {
        return "Grévy's zebra and the mountain zebra" +
                "aims to breed zebras that are phenotypically similar to the quagga" +
                "horses and donkeys" +
                "the plains zebra, the Grévy's zebra and the mountain zebra" +
                "subgenus Hippotigris";
    }

    private RawInput getInputFrom(InputStream inputStream) throws FileNotFoundException {
        Scanner scanner = new Scanner(inputStream);
        RawInput input = new RawInput();
        input.setPassage(scanner.nextLine());
        for(int i = 0; i < 5; i++)
            input.addQuestion(scanner.nextLine());
        input.setAnswers(scanner.nextLine());
        return input;
    }
}
