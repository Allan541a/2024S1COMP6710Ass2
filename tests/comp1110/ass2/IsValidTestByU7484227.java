package comp1110.ass2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.concurrent.TimeUnit;

@Timeout(value = 500, unit = TimeUnit.MILLISECONDS)

public class IsValidTestByU7484227 {
    PathwayCard card = new PathwayCard("hbbrbyrbyb", 'A');

    @Test
    public void testValidDeckLength() {
        String result = card.toString();
        int expectedLength = 10;
        int actualLength = result.length();
        Assertions.assertEquals(expectedLength, actualLength, "For deck string: " + result +
                ", expected length: " + expectedLength + ", but got: " + actualLength);
    }

    @Test
    public void testValidDeckCharacters() {
        String target1 = card.toString();
        String result1 = "hbbrbyrbyb";
        Assertions.assertEquals(target1, result1, "Decks are expected to only have letters as demonstrated in "
                + target1 + " however we got: " + result1);
    }

    @Test
    public void testValidDeckID() {
        String target1 = card.toString();
        String result1 = "hbbrbyrbyb";
        Assertions.assertEquals(target1, result1, "The expected deck IDs are A,B,C,D as seen in "
                + target1 + " however we got: " + result1);
    }

}