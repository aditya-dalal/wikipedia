package processors;

import exceptions.InvalidInputException;

public interface PassageProcessor {
    String getAnswerToQuestion(String question) throws InvalidInputException;
}
