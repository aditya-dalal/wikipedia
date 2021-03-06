package filters;

import exceptions.InvalidInputException;
import lombok.Setter;

@Setter
public class LowerCaseFilter implements FilterChain {

    private FilterChain filterChain;

    @Override
    public String filter(String input) throws InvalidInputException {
        if(input == null)
            throw new InvalidInputException("Input string is null");
        String filteredString = input.toLowerCase();
        return filterChain == null ? filteredString : filterChain.filter(filteredString);
    }
}
