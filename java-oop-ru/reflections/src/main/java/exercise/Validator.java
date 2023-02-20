package exercise;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

// BEGIN
public class Validator {

    public static List<String> validate(Address address) {
        List<String> fieldsMustBeWithoutNull = new ArrayList<>();
        List<Field> fields = getFieldsdWithAnnotation(address);
        try {
            for (Field field : fields) {
                if (field.get(address) == null) {
                    fieldsMustBeWithoutNull.add(field.getName().toString());
                }
            }
            } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
        return fieldsMustBeWithoutNull;
    }

    public static List<Field> getFieldsdWithAnnotation(Address address) {
        List<Field> result = new ArrayList<>();
        var fields = address.getClass().getDeclaredFields();

        for (Field field : fields) {
            field.setAccessible(true);
            if (field.isAnnotationPresent(NotNull.class)) {
                result.add(field);
            }
        }
    return result;
    }
}
// END
