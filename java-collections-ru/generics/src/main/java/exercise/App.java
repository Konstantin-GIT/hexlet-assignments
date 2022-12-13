package exercise;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;


// BEGIN
class App {
    public static List findWhere(List<Map<String, String>> books, Map<String, String> searchedBook) {
        List<Map> result = new ArrayList<>();
        for (var i = 0; i < books.size(); i++) {
            boolean find = true;
            for (Map.Entry<String, String> currentSearchedKeyAndValue : searchedBook.entrySet()) {
                var currentSearchedKey = currentSearchedKeyAndValue.getKey();
                var currentSearchedValue = currentSearchedKeyAndValue.getValue();
                var currentBook = books.get(i);
                if (!currentBook.get(currentSearchedKey).equals(currentSearchedValue)) {
                    find = false;
                }
            }
            if (find) {
                result.add(books.get(i));
            }

        }
        return result;
    }
}
//END
