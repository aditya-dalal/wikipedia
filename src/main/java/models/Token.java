package models;

import lombok.Getter;

@Getter
public class Token {
    private String value;
    private String token;

    public Token(String token, String value) {
        this.value = value;
        this.token = token;
    }
}
