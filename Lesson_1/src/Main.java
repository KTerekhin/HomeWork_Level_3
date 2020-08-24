public class Main {

    public static void main(String[] args) {
        runProgram();
    }

    static void runProgram() {
        doTask1();
        doTask2();
        doTask3();
    }

    static void doTask1() {
        System.out.println("1. Смена элементов массива местами.");
        Task1 task1 = new Task1();

        Integer[] numbers = {1, 2, 3, 4, 5};
        task1.swapElements(numbers, 4, 1);

        String[] words = {"Меркурий", "Венера", "Земля", "Марс", "Юпитер"};
        task1.swapElements(words, "Венера", "Марс");

        Object[] objects = {"Первый", 2, "Третий", 4};
        task1.swapElements(objects, 2, "Третий");
    }

    static void doTask2() {
        System.out.println("2. Преобразование массива в ArrayList.");
        Task2 task2 = new Task2();

        Integer[] testNumbers = {11, 22, 33, 44, 55};
        System.out.println(task2.arrayToList(testNumbers).toString());

        String[] testWords = {"Яблоко", "Апельсин", "Груша", "Банан", "Арбуз"};
        System.out.println(task2.arrayToList(testWords).toString());
    }

    static void doTask3() {
        System.out.println("3. Задание с фруктами.");
        Box<Apple> appleBox1 = new Box<>();
        appleBox1.addFruit(new Apple(), new Apple(), new Apple());
        System.out.println("Вес коробки с яблоками: " + appleBox1.getWeight());


        Box<Orange> orangeBox1 = new Box<>();
        orangeBox1.addFruit(new Orange(), new Orange());
        System.out.println("Вес первой коробки с апельсинами: " + orangeBox1.getWeight());

        Box<Orange> orangeBox2 = new Box<>();
        orangeBox2.addFruit(new Orange(), new Orange(), new Orange(), new Orange());
        System.out.println("Вес второй коробки с апельсинами: " + orangeBox2.getWeight());

        System.out.println("Сравниваем коробки с фруктами...");
        System.out.println(appleBox1.compare(orangeBox1));
        System.out.println(appleBox1.compare(orangeBox2));

        System.out.println("Пересыпаем апельсины во вторую коробку");
        orangeBox1.putInAnotherBox(orangeBox2);
        System.out.println("Вес второй коробки после пересыпания: " + orangeBox2.getWeight());

    }
}
