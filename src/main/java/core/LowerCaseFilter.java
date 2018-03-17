package core;

public class LowerCaseFilter implements FilterChain {

    private FilterChain filterChain;

    @Override
    public String filter(String str) {
        String filteredString = str.toLowerCase();
        return filterChain == null ? filteredString : filterChain.filter(filteredString);
    }

    @Override
    public void setFilterChain(FilterChain filterChain) {
        this.filterChain = filterChain;
    }
}
