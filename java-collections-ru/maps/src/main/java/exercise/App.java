package exercise;

import java.util.HashMap;
import java.util.Map;

// BEGIN
class App {
    public static Map getWordCount(String sentense) {
        Map<String, Integer> wordsFromSentense = new HashMap<>();
        if (sentense.isEmpty()) {
            return wordsFromSentense;
        }
        String[] subStr;
        String delimeter = " ";
        subStr = sentense.split(delimeter);
        //System.out.println("subStr="+ subStr. .toString());
        for (var i = 0; i < subStr.length; i++) {
            String currentWord = subStr[i];
            if (!wordsFromSentense.containsKey(currentWord)) {
                wordsFromSentense.put(currentWord, 1);
            } else {
                var counterWord = wordsFromSentense.get(currentWord);
                wordsFromSentense.put(currentWord, counterWord + 1);
            }
        }
        return wordsFromSentense;
    }

    public static String toString(Map wordsAndQuantity) {
        if (wordsAndQuantity.isEmpty()) {
            return "{}";
        }
        String result = "{";

        for (Object key: wordsAndQuantity.keySet()) {
            result += "\n  " + key + ": " + wordsAndQuantity.get(key);
        }
        return result + "\n}";
    }
}
//END
