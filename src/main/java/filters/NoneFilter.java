package filters;

import exceptions.InvalidInputException;
import lombok.Setter;

@Setter
public class NoneFilter implements FilterChain {

    private FilterChain filterChain;

    @Override
    public String filter(String input) throws InvalidInputException {
        if(input == null)
            throw new InvalidInputException("Input string is null");
        return filterChain == null ? input : filterChain.filter(input);
    }
}
