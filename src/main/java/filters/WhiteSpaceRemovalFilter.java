package filters;

import lombok.Setter;

@Setter
public class WhiteSpaceRemovalFilter implements FilterChain {

    private FilterChain filterChain;

    @Override
    public String filter(String str) {
        String filteredString = str.replaceAll("\\s", "");
        return filterChain == null ? filteredString : filterChain.filter(filteredString);
    }
}
