package rmp;

import org.eclipse.collections.api.bag.ImmutableBag;
import org.eclipse.collections.api.factory.Bags;
import rmp.day2.CubeGame;
import rmp.day2.CubeGame2;

import static org.eclipse.collections.impl.tuple.primitive.PrimitiveTuples.pair;

public class Day02 extends AdventDays {


    public static void main(String[] args) {
        if (args.length != 1)
            System.out.println("Usage: Day02 <inputFileName>");
        long t1 = System.nanoTime();
        String filename = args[0];
        cubeGame1(filename);
        cubeGame2(filename);
        long delta = (System.nanoTime() - t1);
        System.out.println("Delta: " + delta);
    }

    private static void cubeGame1(String filename) {
        ImmutableBag<String> bag = Bags.immutable.ofOccurrences(pair("red", 12), pair("green", 13), pair("blue", 14));
        final CubeGame cubeGame = process(filename, new CubeGame(bag));
        System.out.println("Sum: " + cubeGame.getValidGameIdSum());
    }

    private static void cubeGame2(String filename) {
        final CubeGame2 cubeGame = process(filename, new CubeGame2());
        System.out.println("PowerSum: " + cubeGame.getPowerSum());
    }
}