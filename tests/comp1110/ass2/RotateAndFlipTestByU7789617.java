package comp1110.ass2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.concurrent.TimeUnit;


@Timeout(value = 500, unit = TimeUnit.MILLISECONDS)
public class RotateAndFlipTestByU7789617 {

    //  test card rotating
    PathwayCard card1 = new PathwayCard("abgbbgybby", 'A');
    PathwayCard card2 = new PathwayCard("eyggbggbbr", 'B');

    @Test
    public void testCardRotateNorth(){
        card1.rotate(Orientation.NORTH);
        String result1 = card1.toString();
        String target1 = "abgbbgybby";
        Assertions.assertEquals(target1, result1, "For abgbbgybby rotate NORTH, expected: " + target1 + ", but got: " + result1);

        card2.rotate(Orientation.NORTH);
        String result2 = card2.toString();
        String target2 = "eyggbggbbr";
        Assertions.assertEquals(target2, result2, "For eyggbggbbr rotate NORTH, expected: " + target2 + ", but got: " + result2);
    }

    @Test
    public void testCardRotateEast(){
        card1.rotate(Orientation.EAST);
        String result1 = card1.toString();
        String target1 = "abbbbggyyb";
        Assertions.assertEquals(target1, result1, "For abgbbgybby rotate EAST, expected: " + target1 + ", but got: " + result1);

        card2.rotate(Orientation.EAST);
        String result2 = card2.toString();
        String target2 = "ebbybggrgg";
        Assertions.assertEquals(target2, result2, "For eyggbggbbr rotate EAST, expected: " + target2 + ", but got: " + result2);
    }

    @Test
    public void testCardRotateSouth(){
        card1.rotate(Orientation.SOUTH);
        String result1 = card1.toString();
        String target1 = "aybbygbbgb";
        Assertions.assertEquals(target1, result1, "For abgbbgybby rotate SOUTH, expected: " + target1 + ", but got: " + result1);

        card2.rotate(Orientation.SOUTH);
        String result2 = card2.toString();
        String target2 = "erbbggbggy";
        Assertions.assertEquals(target2, result2, "For eyggbggbbr rotate SOUTH, expected: " + target2 + ", but got: " + result2);
    }

    @Test
    public void testCardRotateWest(){
        card1.rotate(Orientation.WEST);
        String result1 = card1.toString();
        String target1 = "abyyggbbbb";
        Assertions.assertEquals(target1, result1, "For abgbbgybby rotate WEST, expected: " + target1 + ", but got: " + result1);

        card2.rotate(Orientation.WEST);
        String result2 = card2.toString();
        String target2 = "eggrggbybb";
        Assertions.assertEquals(target2, result2, "For eyggbggbbr rotate WEST, expected: " + target2 + ", but got: " + result2);
    }


    //  test fire tile flipping
    FireTile fireTile1 = new FireTile("f000102101120");
    FireTile fireTile2 = new FireTile("p0001111213");

    @Test
    public void testFireTileFlip(){
        fireTile1.flip();
        String result1 = fireTile1.toString();
        String target1 = "f080706181728";
        Assertions.assertEquals(target1, result1, "For f000102101120 flip, expected: " + target1 + ", but got: " + result1);

        fireTile2.flip();
        String result2 = fireTile2.toString();
        String target2 = "p0807171615";
        Assertions.assertEquals(target2, result2, "For p0001111213 flip, expected: " + target2 + ", but got: " + result2);
    }


    //  test fire tile rotate
    @Test
    public void FireTileRotateNorth(){
        fireTile1.rotate(Orientation.NORTH);
        String result1 = fireTile1.toString();
        String target1 = "f000102101120";
        Assertions.assertEquals(target1, result1, "For f000102101120 rotate NORTH, expected: " + target1 + ", but got: " + result1);

        fireTile2.rotate(Orientation.NORTH);
        String result2 = fireTile2.toString();
        String target2 = "p0001111213";
        Assertions.assertEquals(target2, result2, "For p0001111213 rotate NORTH, expected: " + target2 + ", but got: " + result2);
    }

    @Test
    public void testFireTileRotateEast(){
        fireTile1.rotate(Orientation.EAST);
        String result1 = fireTile1.toString();
        String target1 = "f081828071706";
        Assertions.assertEquals(target1, result1, "For f000102101120 rotate EAST, expected: " + target1 + ", but got: " + result1);

        fireTile2.rotate(Orientation.EAST);
        String result2 = fireTile2.toString();
        String target2 = "p0818172737";
        Assertions.assertEquals(target2, result2, "For p0001111213 rotate EAST, expected: " + target2 + ", but got: " + result2);
    }

    @Test
    public void testFireTileRotateSouth(){
        fireTile1.rotate(Orientation.SOUTH);
        String result1 = fireTile1.toString();
        String target1 = "f888786787768";
        Assertions.assertEquals(target1, result1, "For f000102101120 rotate SOUTH, expected: " + target1 + ", but got: " + result1);

        fireTile2.rotate(Orientation.SOUTH);
        String result2 = fireTile2.toString();
        String target2 = "p8887777675";
        Assertions.assertEquals(target2, result2, "For p0001111213 rotate SOUTH, expected: " + target2 + ", but got: " + result2);
    }

    @Test
    public void testFireTileRotateWest(){
        fireTile1.rotate(Orientation.WEST);
        String result1 = fireTile1.toString();
        String target1 = "f807060817182";
        Assertions.assertEquals(target1, result1, "For f000102101120 rotate WEST, expected: " + target1 + ", but got: " + result1);

        fireTile2.rotate(Orientation.WEST);
        String result2 = fireTile2.toString();
        String target2 = "p8070716151";
        Assertions.assertEquals(target2, result2, "For p0001111213 rotate WEST, expected: " + target2 + ", but got: " + result2);
    }
}
