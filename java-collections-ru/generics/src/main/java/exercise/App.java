package exercise;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;


// BEGIN
class App {
    public static List findWhere(List<Map<String, String>> books, Map<String, String> searchedBook) {
        List<Map> result = new ArrayList<>();
        int sizeSearchedBook = searchedBook.size();
        for (var i = 0; i < books.size(); i++) {
            int status = 0;
            for (Map.Entry<String, String> currentSearchedKeyAndValue : searchedBook.entrySet()) {
                var currentSearchedKey = currentSearchedKeyAndValue.getKey();
                var currentSearchedValue = currentSearchedKeyAndValue.getValue();
                var currentBook = books.get(i);
                if (!currentBook.get(currentSearchedKey).equals(currentSearchedValue)) {
                    break;
                }
                status++;
            }
            if (sizeSearchedBook == status) {
                result.add(books.get(i));
            }
        }
        return result;
    }
}
//END
