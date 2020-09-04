package Task_1_From_Website;

public class Truck extends Vehicle {
    public Truck(Race race, float fuelTank, float consumption, String name) {
        this.race = race;
        this.fuelTank = fuelTank;
        this.consumption = consumption;
        this.name = name;
    }

    public void run() {
        for (int i = 0; i < race.getStages().size(); i++) {
            race.getStages().get(i).go(this);
        }
    }
}
