package models;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class RawInput {
    private String passage;
    private String answers;
    private List<String> questions;

    public RawInput() {
        questions = new ArrayList<>();
    }

    public void addQuestion(String question) {
        questions.add(question);
    }
}
