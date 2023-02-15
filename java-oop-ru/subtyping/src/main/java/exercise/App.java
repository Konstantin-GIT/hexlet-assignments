package exercise;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

// BEGIN
public class App {

    public static Map<String, String> swapKeyValue(KeyValueStorage storage) {
        for (String key : storage.toMap().keySet()) {
            String newValue = key;
            String newKey = storage.get(key, "");
            storage.unset(key);
            storage.set(newKey, newValue);
        }
        return storage.toMap();
    }
}
// END
