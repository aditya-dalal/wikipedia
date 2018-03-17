import core.*;
import data.Tokens;
import models.Passage;

import java.util.Arrays;
import java.util.List;

public class Client {

    public static void main(String[] args) {

        String passage = "Zebras are several species of African equids (horse family) united by their distinctive black " +
                "and white stripes. Their stripes come in different patterns, unique to each individual. They are " +
                "generally social animals that live in small harems to large herds. Unlike their closest relatives, " +
                "horses and donkeys, zebras have never been truly domesticated. There are three species of zebras: the " +
                "plains zebra, the Grévy's zebra and the mountain zebra. The plains zebra and the mountain zebra belong " +
                "to the subgenus Hippotigris, but Grévy's zebra is the sole species of subgenus Dolichohippus. The " +
                "latter resembles an ass, to which it is closely related, while the former two are more horse-like. All " +
                "three belong to the genus Equus, along with other living equids. The unique stripes of zebras make them " +
                "one of the animals most familiar to people. They occur in a variety of habitats, such as grasslands, " +
                "savannas, woodlands, thorny scrublands, mountains, and coastal hills. However, various anthropogenic " +
                "factors have had a severe impact on zebra populations, in particular hunting for skins and habitat " +
                "destruction. Grévy's zebra and the mountain zebra are endangered. While plains zebras are much more " +
                "plentiful, one subspecies - the Quagga - became extinct in the late 19th century. Though there is " +
                "currently a plan, called the Quagga Project, that aims to breed zebras that are phenotypically similar " +
                "to the Quagga, in a process called breeding back.";
        String answers = "subgenus Hippotigris; the plains zebra, the Grévy's zebra and the mountain zebra;horses and " +
                "donkeys;aims to breed zebras that are phenotypically similar to the quagga; Grévy's zebra and the " +
                "mountain zebra";
        List<String> questions = Arrays.asList(
                "Which Zebras are endangered?",
                "What is the aim of the Quagga Project?",
                "Which animals are some of their closest relatives?",
                "Which are the three species of zebras?",
                "Which subgenus do the plains zebra and the mountain zebra belong to?");

        PassageProcessor zebraPassageProcessor = getPassageProcessor(passage, answers);

        for (String question: questions)
            System.out.println(zebraPassageProcessor.getAnswerToQuestion(question));
    }

    private static PassageProcessor getPassageProcessor(String passage, String answers) {
        FilterChain filterChain = getFilterChain();
        Tokens zebraPassageTokens = getSentenceTokens(passage, filterChain);
        Tokens zebraAnswerTokens = getAnswerTokens(answers, filterChain);
        TokenMapper mapper = new LCSTokenMapper(new LCS());
        Passage zebraPassage = new Passage(zebraPassageTokens, zebraAnswerTokens, filterChain, mapper);
        return new TokenizedLCSPassageProcessor(zebraPassage);
    }

    private static Tokens getAnswerTokens(String answers, FilterChain filterChain) {
        Tokenizer tokenizer = new AnswerTokenizer(filterChain);
        return tokenizer.generateTokens(answers);
    }

    private static Tokens getSentenceTokens(String passage, FilterChain filterChain) {
        Tokenizer tokenizer = new PassageTokenizer(filterChain);
        return tokenizer.generateTokens(passage);
    }

    private static FilterChain getFilterChain() {
        FilterChain lowerCaseFilter = new LowerCaseFilter();
        FilterChain whiteSpaceRemovalFilter = new WhiteSpaceRemovalFilter();
        lowerCaseFilter.setFilterChain(whiteSpaceRemovalFilter);
        return lowerCaseFilter;
    }
}
