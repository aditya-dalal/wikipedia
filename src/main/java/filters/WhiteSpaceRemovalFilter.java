package filters;

import exceptions.InvalidInputException;
import lombok.Setter;

@Setter
public class WhiteSpaceRemovalFilter implements FilterChain {

    private FilterChain filterChain;
    private static final String WHITESPACES = "\\s";

    @Override
    public String filter(String input) throws InvalidInputException {
        if(input == null)
            throw new InvalidInputException("Input string is null");
        String filteredString = input.replaceAll(WHITESPACES, "");
        return filterChain == null ? filteredString : filterChain.filter(filteredString);
    }
}
