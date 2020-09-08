package Task_1_From_Website;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        runProgram();
    }

    static void runProgram() {
        Race race = new Race(new Road(), new FuelStation(),new Road());
        List<Vehicle> vehicles = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            vehicles.add(new Car(race,20F, 2.5F, "Машина №:" + i));
        }

        for (int i = 0; i < 7; i++) {
            vehicles.add(new Bus(race,20F, 2.5F, "Автобус №:" + i));
        }

        for (int i = 0; i < 7; i++) {
            vehicles.add(new Truck(race,20F, 2.5F, "Грузовик №:" + i));
        }

        for (int i = 0; i < vehicles.size(); i++) {
            int finalI = i;
            new Thread(() -> vehicles.get(finalI).run()).start();
        }
    }
}
