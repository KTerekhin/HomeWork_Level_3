import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Box<B extends Fruit> {
    private List<B> fruitsInBox = new ArrayList<>();

    public float getWeight() {
        if (fruitsInBox.size() > 0) {
            return fruitsInBox.size() * fruitsInBox.get(0).weight;
        } else {
            return 0;
        }
    }

    public void addFruit(B... fruits) {
        Collections.addAll(fruitsInBox, fruits);
    }


    public boolean compare(Box<? extends Fruit> anotherBox) {
        return (Math.abs(getWeight() - anotherBox.getWeight())) < 0.0001f;
    }

    public void putInAnotherBox(Box<B> anotherBox) {
        if (fruitsInBox.size() > 0) {
            anotherBox.fruitsInBox.addAll(fruitsInBox);
            fruitsInBox.clear();
        } else {
            System.out.println("Box is empty.");
        }
    }
}
