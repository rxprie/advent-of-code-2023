package rmp.day2;

import org.eclipse.collections.api.bag.ImmutableBag;
import org.eclipse.collections.api.factory.Bags;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.eclipse.collections.impl.tuple.primitive.PrimitiveTuples.pair;

public class CubeGameTest {


    @Test
    public void cubeGamesTest() {
        String[] input = new String[]{
                "Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green",
                "Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue",
                "Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red",
                "Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red",
                "Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green"
        };
        testCubeGame1(8, input);
        testCubeGame2(2286, input);
    }

    private static void testCubeGame1(long match, String[] input) {
        ImmutableBag<String> bag = Bags.immutable.ofOccurrences(pair("red", 12), pair("green", 13), pair("blue", 14));
        CubeGame cubeGame = new CubeGame(bag);
        Arrays.stream(input).forEach(i -> cubeGame.accept(i));
        Assertions.assertEquals(match, cubeGame.getValidGameIdSum(), "game id sum does not match");
    }

    private static void testCubeGame2(long match, String[] input) {
        CubeGame2 cubeGame = new CubeGame2();
        Arrays.stream(input).forEach(i -> cubeGame.accept(i));
        Assertions.assertEquals(match, cubeGame.getPowerSum(), "power sum does not match!");
    }
}
