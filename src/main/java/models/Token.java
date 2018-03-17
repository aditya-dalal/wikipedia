package models;

public class Token {
    private String value;
    private String token;

    public Token(String token, String value) {
        this.value = value;
        this.token = token;
    }

    public String getToken() {
        return this.token;
    }

    public String getValue() {
        return this.value;
    }
}
