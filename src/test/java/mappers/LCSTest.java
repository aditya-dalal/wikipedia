package mappers;

import groups.UnitTests;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import testData.TestData;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@Category(UnitTests.class)
@RunWith(Parameterized.class)
public class LCSTest {

    private LCS lcs;
    private String token;
    private String input;
    private int expected;

    public LCSTest(String token, String input, int expected) {
        this.token = token;
        this.input = input;
        this.expected = expected;
    }

    @Before
    public void setup() {
        lcs = new LCS();
    }

    @Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                {TestData.getNewToken().getToken(), "areendangered", 13},
                {TestData.getNewToken().getToken(), "which", 5},
                {null, "areendangered", 0},
                {"", "areendangered", 0},
                {TestData.getNewToken().getToken(), null, 0},
                {TestData.getNewToken().getToken(), "", 0},
                {"abcd", "efgh", 0},
        });
    }

    @Test
    public void testGetLCSLengthForValidInput() {
        int length = lcs.getLCSLength(token, input);
        assertEquals(expected, length);
    }
}
