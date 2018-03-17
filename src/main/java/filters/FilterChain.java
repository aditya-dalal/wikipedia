package filters;

public interface FilterChain {
    String filter(String str);
    void setFilterChain(FilterChain filterChain);
}
