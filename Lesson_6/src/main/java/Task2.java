public class Task2 {

    public static boolean checkArrayForOneAndFour(int[] array) {
        boolean one = false;
        boolean four = false;
        for (int i : array) {
            if (i == 1) one = true;
            if (i == 4) four = true;
        }
        return one && four;
    }
}
