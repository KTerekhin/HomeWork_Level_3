import java.util.Arrays;

public class Task1<T> {

    public void swapElements(T[] arr, T firstElement, T secondElement){
        System.out.println("Массив до смены мест элементов: " + Arrays.toString(arr));
        try {
            int indexFirst = Arrays.asList(arr).indexOf(firstElement);
            int indexSecond = Arrays.asList(arr).indexOf(secondElement);
            arr[indexFirst] = secondElement;
            arr[indexSecond] = firstElement;
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Неверный индекс. Метод прерван.");
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Массив после смены мест элементов: " + Arrays.toString(arr));
    }
}
