package exercise;


import java.util.HashMap;
import java.util.Map;

// BEGIN
class App {
    public static Map<String, String> genDiff(Map<String, Object> data1, Map<String, Object> data2) {
        Map<String, String> mapResult = new HashMap<>();

        for (Map.Entry<String, Object> item: data1.entrySet()) {
            if (data2.containsKey(item.getKey()) && data2.get(item.getKey()).equals(item.getValue())) {
                mapResult.put(item.getKey(), "unchanged");
            } else if (data2.containsKey(item.getKey()) && !data2.get(item.getKey()).equals(item.getValue())) {
                mapResult.put(item.getKey(), "changed");
            } else if (!data2.containsKey(item.getKey())) {
                mapResult.put(item.getKey(), "deleted");
            }
        }
        for (Map.Entry<String, Object> item: data2.entrySet()) {
            if (!data1.containsKey(item.getKey())) {
                mapResult.put(item.getKey(), "added");
            }
        }
        return mapResult;
    }

}
//END
