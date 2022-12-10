package exercise;

import java.util.ArrayList;
import java.util.List;

// BEGIN
class App {
    public static boolean scrabble(String characters, String word) {
        var lengthWord = word.length();
        var lengthCharacter = characters.length();
        if (lengthWord > lengthCharacter) {
            return false;
        }
        List<Character> charactersList = new ArrayList<>();
        for (var i = 0; i < lengthCharacter; i++) {
            var currentCharToLower = Character.toLowerCase(characters.charAt(i));
            charactersList.add(currentCharToLower);
        }
        System.out.println(charactersList);
        List<Character> wordList = new ArrayList<>();
        for (var i = 0; i < lengthWord; i++) {
            var currentCharToLower = Character.toLowerCase(word.charAt(i));
            wordList.add(currentCharToLower);
        }
        for (var i = 0; i < lengthWord; i++) {
            int resultIndex = charactersList.indexOf(wordList.get(i));
            if (resultIndex != -1) {
                charactersList.remove(resultIndex);
            } else {
                return false;
            }
        }
        return true;
    }
}
//END
