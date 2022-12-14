package exercise;

//import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class AppTest {

    @Test
    void testTake() {
        // BEGIN
        int count1 = 4;
        List<Integer> numbers1 = new ArrayList<>(Arrays.asList(1, 2, 3, 4));
        Assertions.assertEquals(App.take(numbers1, count1).size(), 4);

        int count2 = 0;
        List<Integer> numbers2 = new ArrayList<>(Arrays.asList(1, 2, 3, 4));
        Assertions.assertEquals(App.take(numbers2, count2).size(), 0);

        int count3 = 2;
        List<Integer> numbers3 = new ArrayList<>();
        Assertions.assertEquals(App.take(numbers3, count3).size(), 0);

        int count4 = 2;
        List<Integer> numbers4 = new ArrayList<>(Arrays.asList(1, 2, 3, 4));
        Assertions.assertEquals(App.take(numbers4, count4).size(), 2);

        int count5 = 2;
        List<Integer> numbers5 = new ArrayList<>(Arrays.asList(1, 2, 3, 4));
        Assertions.assertEquals(App.take(numbers5, count5).get(0), 1);
        // END
    }
}
