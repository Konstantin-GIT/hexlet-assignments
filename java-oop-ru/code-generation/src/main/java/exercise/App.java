package exercise;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import static java.nio.charset.StandardCharsets.UTF_8;

// BEGIN
public class App {

    public static void save(Path path, Car car) throws JsonProcessingException {
        String text = car.serialize();
        try {
            Files.write(path, text.getBytes(), StandardOpenOption.APPEND);
            System.out.println("Successfully written bytes to the file");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Car extract(Path path) throws IOException {
        String fileContents = Files.readString(path);
        return Car.unserialize(fileContents);

    }
}
// END
