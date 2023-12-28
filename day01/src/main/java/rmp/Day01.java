package rmp;

import org.eclipse.collections.api.IntIterable;
import org.eclipse.collections.api.RichIterable;
import org.eclipse.collections.impl.list.mutable.ListAdapter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Day01 {

    private static RichIterable<String> load(String fileName) {
        final Path path = Paths.get(fileName);
        if (Files.isRegularFile(path)) {
            try {
                return ListAdapter.adapt(Files.readAllLines(path)).reject(String::isBlank);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        throw new IllegalArgumentException("Cannot find filename " + path.toAbsolutePath());
    }

    public static void main(String[] args) {
        if (args.length != 1)
            System.out.println("Usage: Day01 <inputFileName>");
        CalibrationValuesParser calibrationValuesParser = new CalibrationValuesParser();
        IntIterable values = calibrationValuesParser.values(load(args[0]));
        System.out.println("Values: " + values);
        System.out.println("Sum: " + values.sum());
    }
}