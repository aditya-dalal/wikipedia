package data;

import models.Token;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Tokens implements Iterable<Token> {
    private List<Token> tokens;

    public Tokens() {
        tokens = new ArrayList<>();
    }

    public void addToken(Token token) {
        tokens.add(token);
    }

    @Override
    public Iterator<Token> iterator() {
        return tokens.iterator();
    }
}
