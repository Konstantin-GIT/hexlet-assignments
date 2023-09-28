package exercise;

import exercise.model.Address;
import exercise.annotation.Inspect;
import java.lang.reflect.Method;

public class Application {
    public static void main(String[] args) {
        var address = new Address("London", 12345678);

        // BEGIN
// Iterate through all methods of the class
        for (Method method : Address.class.getDeclaredMethods()) {
            String currentMethod = method.getName();
            // Check if the method has the @LogExecutionTime annotation
            if (method.isAnnotationPresent(Inspect.class)) {
                System.out.println("Method " + method.getName() + " returns a value of type "
                + method.getReturnType().getSimpleName());
            }
        }
        // END
    }
}
