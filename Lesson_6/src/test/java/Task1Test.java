import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class Task1Test {
    private Task1 task1;

    @BeforeEach
    public void init() {
        task1 = new Task1();
    }

    @Test
    public void testDoArrayAfterLastFour() {
        Assertions.assertArrayEquals(new int[]{6,1}, task1.doArrayAfterLastFour(new int[]{1, 2, 4, 4, 2, 3, 4, 6, 1}));
    }

    public static Stream<Object[]> arrays() {
        return Stream.of(new Object[][]{
                {new int[]{1, 7}, new int[]{1, 2, 4, 4, 2, 3, 4, 1, 7}},
                {new int[]{15, 244, 53, 0, 17, 17}, new int[]{4, 15, 244, 53, 0, 17, 17}},
                {new int[]{}, new int[]{9, 4, 4, 11, 23, 4}}
        });
    }

    @ParameterizedTest
    @MethodSource("arrays")
    public void testDoArrayAfterLastFour(int[] expectedArray, int[] myArray) {
        Assertions.assertArrayEquals(expectedArray, task1.doArrayAfterLastFour(myArray));
    }

    public static Stream<Object[]> arraysWithExceptions() {
        return Stream.of(new Object[][]{
                {new int[]{1, 2, 2, 3, 1, 7}},
                {new int[]{15, 244, 53, 0, 17, 17}},
                {new int[]{9, 11, 23}}
        });
    }

    @ParameterizedTest
    @MethodSource("arraysWithExceptions")
    public void testDoArrayAfterLastFour(int[] myArray) {
        Assertions.assertThrows(RuntimeException.class, () -> task1.doArrayAfterLastFour(myArray));
    }
}
