package rmp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.function.Consumer;

public class AdventDays {
    protected static <C extends Consumer<String>> C process(File file, C lineConsumer) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String line = bufferedReader.readLine();
            while (line != null) {
                lineConsumer.accept(line);
                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return lineConsumer;
    }

    protected static <C extends Consumer<String>> C process(String fileName, C lineConsumer) {
        final Path path = Paths.get(fileName);
        if (Files.isRegularFile(path)) {
            return process(path.toFile(), lineConsumer);
        }
        throw new IllegalArgumentException("Invalid input file '" + path.toAbsolutePath() + "'");
    }

}
