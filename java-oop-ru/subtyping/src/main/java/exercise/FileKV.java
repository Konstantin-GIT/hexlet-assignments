package exercise;

import java.util.HashMap;
import java.util.Map;

// BEGIN
public class FileKV implements KeyValueStorage  {
    private final String filepath;
    private final Map<String, String> storage;

    public FileKV(String filepath, Map<String, String> storage) {
        this.filepath = filepath;
        this.storage = new HashMap<>(storage);
    }

    @Override
    public void set(String key, String value) {
        storage.put(key, value);
    }

    @Override
    public void unset(String key) {

        storage.remove(key);
    }

    @Override
    public String get(String key, String defaultValue) {
        String result = storage.get(key);
        if (result == null) {
            return defaultValue;
        }
        return result;
    }

    @Override
    public Map<String, String> toMap() {
        return new HashMap<>(storage);
    }

}
// END
