package rmp;

import org.eclipse.collections.api.IntIterable;
import org.eclipse.collections.api.RichIterable;

import java.util.Optional;

public class CalibrationValuesParser {

    private final String[] wordDigits = new String[]{"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};

    public CalibrationValuesParser() {
    }

    public IntIterable values(RichIterable<String> lines) {
        return lines.collectInt(this::parseCalibrationLine);
    }

    private int parseCalibrationLine(String line) {
        return (findFirstDigit(line) * 10) + findLastDigit(line);
    }

    private int findLastDigit(String line) {
        for (int index = line.length() - 1; index >= 0; index--) {
            Optional<Integer> digit = getDigitInLineAtIndex(line, index);
            if (digit.isPresent())
                return digit.get();
        }
        throw new IllegalStateException("Line should have at least one digit!");
    }

    private int findFirstDigit(String line) {
        for (int index = 0; index < line.length(); index++) {
            Optional<Integer> digit = getDigitInLineAtIndex(line, index);
            if (digit.isPresent())
                return digit.get();
        }
        throw new IllegalStateException("Line should have at least one digit!");
    }

    private static int asInt(byte ch) {
        return ch - (byte) '0';
    }

    private Optional<Integer> getDigitInLineAtIndex(String line, int lineIndex) {
        final char ch = line.charAt(lineIndex);
        if (Character.isDigit(ch))
            return Optional.of(asInt((byte) ch));
        Integer digit = wordDigit(line, lineIndex);
        return digit == null ? Optional.empty() : Optional.of(digit);
    }

    private Integer wordDigit(String line, int lineIndex) {
        for (int wordIndex = 1; wordIndex < wordDigits.length; wordIndex++) {
            if (wordMatch(wordDigits[wordIndex], line, lineIndex))
                //word digit's index is matching number!
                return wordIndex;
        }
        return null;
    }

    private boolean wordMatch(String wordDigit, String line, int lineIndex) {
        if (line.length() < (lineIndex + wordDigit.length()))
            return false;
        for (int index = wordDigit.length() - 1; index >= 0; index--) {
            if (wordDigit.charAt(index) != line.charAt(index + lineIndex))
                return false;
        }
        return true;
    }
}
