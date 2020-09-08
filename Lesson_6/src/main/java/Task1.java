import java.util.Arrays;

public class Task1 {

    public int[] doArrayAfterLastFour(int[] array) {
        int[] newArray = new int[array.length];
        System.arraycopy(array, 0, newArray,0,array.length);
        for (int i = newArray.length - 1; i >= 0; i--) {
            if (newArray[i] == 4) {
                return Arrays.copyOfRange(newArray, i + 1, newArray.length);
            }
        }
        throw new RuntimeException("В этом массиве нет 4!");
    }
}
