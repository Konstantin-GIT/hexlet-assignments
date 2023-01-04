package exercise;

//import java.util.Comparator;
import java.util.Map;
import java.util.List;
//import java.time.LocalDate;
import java.util.stream.Collectors;

// BEGIN
class Sorter {

    public static List<String> takeOldestMans(List<Map<String, String>>  listUsers) {
        return listUsers.stream()
            .filter(map -> map.get("gender").equals("male"))
            .sorted((map1, map2) -> map1.get("birthday").compareTo(map2.get("birthday")))
            .map(map -> map.get("name"))
            .collect(Collectors.toList());
    }
}
// END
