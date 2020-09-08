import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class Task2Test {
    private Task2 task2;

    @BeforeEach
    public void init() {
        task2 = new Task2();
    }

    @Test
    public void testCheckArrayForOneAndFour() {
        Assertions.assertTrue(task2.checkArrayForOneAndFour(new int[]{1, 1, 4, 4, 4}));
    }

    public static Stream<Object> arrays() {
        return Stream.of(new Object[]{
                new int[]{1, 1, 4, 4, 4, 4, 4, 1, 1},
                new int[]{4, 1, 4, 1, 4, 1, 1},
                new int[]{4, 4, 4, 1, 4, 4}
        });
    }

    @ParameterizedTest
    @MethodSource("arrays")
    public void testCheckArrayForOneAndFour1(int[] myArray) {
        Assertions.assertTrue(task2.checkArrayForOneAndFour(myArray));
    }

    public static Stream<Object> arraysWithoutFourAndOne() {
        return Stream.of(new Object[]{
                new int[]{2, 2, 3, 7},
                new int[]{15, 244, 53, 0, 17, 17},
                new int[]{1, 1, 1},
                new int[]{4, 4, 4},
                new int[]{9, 11, 23},
        });
    }

    @ParameterizedTest
    @MethodSource("arraysWithoutFourAndOne")
    public void testCheckArrayForOneAndFour2(int[] myArray) {
        Assertions.assertFalse(task2.checkArrayForOneAndFour(myArray));
    }
}
