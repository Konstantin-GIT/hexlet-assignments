package exercise;

import lombok.Data;
import lombok.Value;

// BEGIN
@Value
// END
class User {

 /* public User(int id, String firstName, String lastName, int age) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }*/
    int id;
    String firstName;
    String lastName;
    int age;
}
