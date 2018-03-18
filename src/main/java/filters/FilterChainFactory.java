package filters;

import exceptions.InvalidInputException;

public class FilterChainFactory {

    public static FilterChain getFilterChain(FilterType filterType) throws InvalidInputException {
        if(filterType == null)
            throw new InvalidInputException("Filter type cannot be null");
        switch (filterType) {
            case ALL:
                return getAllFilterChain();
            case LOWERCASE:
                return new LowerCaseFilter();
            case WHITESPACE_REMOVAL:
                return new WhiteSpaceRemovalFilter();
            case NONE:
                return new NoneFilter();
            default:
                throw new InvalidInputException("Unknown filter type: " + filterType.toString());
        }
    }

    private static FilterChain getAllFilterChain() {
        FilterChain lowerCaseFilter = new LowerCaseFilter();
        FilterChain whiteSpaceRemovalFilter = new WhiteSpaceRemovalFilter();
        lowerCaseFilter.setFilterChain(whiteSpaceRemovalFilter);
        return lowerCaseFilter;
    }
}
