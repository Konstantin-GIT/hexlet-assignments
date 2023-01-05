package exercise;

//import java.util.List;
import java.util.stream.Collectors;
import java.util.Arrays;

// BEGIN
class App {
    public static String getForwardedVariables(String content) {
        String[] contentByLine =  content.split("\\n");
        String contentByEnvironment = Arrays.stream(contentByLine)
                .filter(word -> word.startsWith("environment"))
                .collect(Collectors.joining(","));
        String[] contentForStream =  contentByEnvironment.split("\"|,");
        return Arrays.stream(contentForStream)
                .filter(word -> word.startsWith("X_FORWARDED_"))
                .map(word -> word.replaceAll("X_FORWARDED_", ""))
                .collect(Collectors.joining(","));
    }
}


//END
