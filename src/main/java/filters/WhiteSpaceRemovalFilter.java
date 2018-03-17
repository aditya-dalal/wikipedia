package filters;

import exceptions.InvalidInputException;
import lombok.Setter;

@Setter
public class WhiteSpaceRemovalFilter implements FilterChain {

    private FilterChain filterChain;

    @Override
    public String filter(String str) throws InvalidInputException {
        if(str == null)
            throw new InvalidInputException("Input string is null");
        String filteredString = str.replaceAll("\\s", "");
        return filterChain == null ? filteredString : filterChain.filter(filteredString);
    }
}
