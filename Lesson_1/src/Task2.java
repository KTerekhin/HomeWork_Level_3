import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Task2<G> {
    public List<G> arrayToList(G[] array) {
        List<G> list = new ArrayList<>(Arrays.asList(array));
        return list;
    }
}
