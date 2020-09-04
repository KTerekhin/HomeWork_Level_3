package Task_2;

import java.util.concurrent.CountDownLatch;

public class MainClass {
    public static final int CARS_COUNT = 4;
    public static volatile boolean winner = false;

    public static void main(String[] args) {
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!");
        Race race = new Race(new Road(60), new Tunnel(100), new Road(40));
        Car[] cars = new Car[CARS_COUNT];
        CountDownLatch carsAreReady = new CountDownLatch(CARS_COUNT);
        CountDownLatch carsHaveFinished = new CountDownLatch(CARS_COUNT);

        for (int i = 0; i < cars.length; i++) {
            cars[i] = new Car(race, 20 + (int) (Math.random() * 10), carsAreReady, carsHaveFinished);
        }

        for (Car car : cars) {
            new Thread(car).start();
        }

        try {
            carsAreReady.await();
            System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!");
            carsHaveFinished.await();
            System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
