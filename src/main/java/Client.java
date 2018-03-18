import exceptions.InvalidInputException;
import filters.FilterType;
import models.RawInput;
import processors.PassageProcessor;
import processors.PassageProcessorFactory;
import processors.ProcessorType;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

public class Client {

    private static final String INPUT_FILE = "/input.txt";

    public static void main(String[] args) throws FileNotFoundException, InvalidInputException {
        Client client = new Client();
        InputStream inputStream = client.getClass().getResourceAsStream(INPUT_FILE);
        RawInput input = getInputFrom(inputStream);

        PassageProcessor passageProcessor = PassageProcessorFactory.getPassageProcessor(ProcessorType.TOKENIZED_LCS_PROCESSOR,
                FilterType.ALL, input);
        for (String question: input.getQuestions())
            System.out.println(passageProcessor.getAnswerToQuestion(question));
    }

    private static RawInput getInputFrom(InputStream inputStream) throws FileNotFoundException {
        Scanner scanner = new Scanner(inputStream);
        RawInput input = new RawInput();
        input.setPassage(scanner.nextLine());
        for(int i = 0; i < 5; i++)
            input.addQuestion(scanner.nextLine());
        input.setAnswers(scanner.nextLine());
        return input;
    }

}
