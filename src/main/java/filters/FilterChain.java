package filters;

import exceptions.InvalidInputException;

public interface FilterChain {
    String filter(String str) throws InvalidInputException;
    void setFilterChain(FilterChain filterChain);
}
