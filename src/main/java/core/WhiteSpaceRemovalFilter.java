package core;

public class WhiteSpaceRemovalFilter implements FilterChain {

    private FilterChain filterChain;

    @Override
    public String filter(String str) {
        String filteredString = str.replaceAll("\\s", "");
        return filterChain == null ? filteredString : filterChain.filter(filteredString);
    }

    @Override
    public void setFilterChain(FilterChain filterChain) {
        this.filterChain = filterChain;
    }
}
