package Task_1_From_Website;

import java.util.concurrent.Semaphore;

public class FuelStation extends Stage {
    private static final Semaphore semaphore = new Semaphore(3);

    public void refuel(Vehicle v) {
        try {
            try {
                System.out.println(v.getName() + " готовится к заправке.");
                semaphore.acquire();
                System.out.println(v.getName() + " запраляется.");
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println(v.getName() + " закончил заправку.");
                semaphore.release();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void go(Vehicle v) {
        refuel(v);
    }
}
