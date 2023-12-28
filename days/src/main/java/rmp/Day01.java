package rmp;

import rmp.day1.CalibrationValuesParser;

public class Day01 extends AdventDays {


    public static void main(String[] args) {
        if (args.length != 1)
            System.out.println("Usage: Day01 <inputFileName>");
        long t1 = System.nanoTime();
        String filename = args[0];
        final CalibrationValuesParser calibrationValuesParser = process(filename, new CalibrationValuesParser());
        System.out.println("Sum: " + calibrationValuesParser.sum());
        long delta = (System.nanoTime() - t1);
        System.out.println("Delta: " + delta);
    }
}