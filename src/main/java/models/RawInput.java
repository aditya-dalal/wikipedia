package models;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class RawInput {
    @Setter
    private String passage;
    @Setter
    private String answers;
    private List<String> questions;

    public RawInput() {
        questions = new ArrayList<>();
    }

    public void addQuestion(String question) {
        questions.add(question);
    }
}
