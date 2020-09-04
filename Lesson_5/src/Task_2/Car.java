package Task_2;

import java.util.concurrent.CountDownLatch;

public class Car implements Runnable {
    private static int CARS_COUNT;
    private final CountDownLatch carIsReady;
    private final CountDownLatch carsHaveFinished;
    private final Race race;
    private final int speed;
    private final String name;

    static {
        CARS_COUNT = 0;
    }

    public String getName() {
        return name;
    }

    public int getSpeed() {
        return speed;
    }

    public Car(Race race, int speed, CountDownLatch carIsReadyToStartRace, CountDownLatch carsHaveFinished) {
        this.race = race;
        this.speed = speed;
        this.carIsReady = carIsReadyToStartRace;
        this.carsHaveFinished = carsHaveFinished;
        CARS_COUNT++;
        this.name = "Участник #" + CARS_COUNT;
    }

    @Override
    public void run() {
        try {
            System.out.println(this.name + " готовится");
            Thread.sleep(500 + (int) (Math.random() * 800));
            System.out.println(this.name + " готов");
            carIsReady.countDown();
            carIsReady.await();
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (int i = 0; i < race.getStages().size(); i++) {
            race.getStages().get(i).go(this);
            if (i == race.getStages().size() - 1) {
                if (!MainClass.winner) {
                    MainClass.winner = true;
                    System.out.println("Победитель: " + getName());
                }
            }
        }
        carsHaveFinished.countDown();
    }
}
