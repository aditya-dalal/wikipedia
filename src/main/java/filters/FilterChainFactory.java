package filters;

public class FilterChainFactory {

    public static FilterChain getFilterChain(FilterType filterType) {
        switch (filterType) {
            case ALL:
                return getAllFilterChain();
            case LOWERCASE:
                return new LowerCaseFilter();
            case WHITESPACE_REMOVAL:
                return new WhiteSpaceRemovalFilter();
            default:
                return null;
        }
    }

    private static FilterChain getAllFilterChain() {
        FilterChain lowerCaseFilter = new LowerCaseFilter();
        FilterChain whiteSpaceRemovalFilter = new WhiteSpaceRemovalFilter();
        lowerCaseFilter.setFilterChain(whiteSpaceRemovalFilter);
        return lowerCaseFilter;
    }
}
