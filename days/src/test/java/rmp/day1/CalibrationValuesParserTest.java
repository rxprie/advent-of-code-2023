package rmp.day1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class CalibrationValuesParserTest {


    @Test
    public void calibrationValueTest1() {
        String[] input = new String[]{
                "1abc2",
                "pqr3stu8vwx",
                "a1b2c3d4e5f",
                "treb7uchet"
        };
        test(142, input);
    }

    @Test
    public void calibrationValueTest2() {
        String[] input = new String[]{
                "two1nine",
                "eightwothree",
                "abcone2threexyz",
                "xtwone3four",
                "4nineeightseven2",
                "zoneight234",
                "7pqrstsixteen"
        };
        test(281, input);
    }


    private static void test(long match, String[] input) {
        CalibrationValuesParser calibrationValuesParser = new CalibrationValuesParser();
        Arrays.stream(input).forEach(i -> calibrationValuesParser.accept(i));
        Assertions.assertEquals(match, calibrationValuesParser.sum());
    }
}
