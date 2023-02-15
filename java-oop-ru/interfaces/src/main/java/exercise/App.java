package exercise;

import java.util.List;
import java.util.stream.Collectors;

// BEGIN
class App {
    public static List<String> buildApartmentsList(List<Home> realEstateObjects, int numberOfRealEstateObjects) {
        return realEstateObjects.stream()
                .limit(numberOfRealEstateObjects)
                .sorted((object1, object2)
                        -> Double.compare(object1.getArea(), object2.getArea()))
                .map(object -> object.toString())
                .collect(Collectors.toList());
    }
}
// END
