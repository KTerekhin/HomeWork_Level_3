import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Comparator;

public class Main {

    public static void main(String[] args) throws IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException {
        start(TestClass.class);
    }

    public static void start(Class testClass) throws IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException {
        Method[] methods = testClass.getDeclaredMethods();
        ArrayList<Method> annotatedMethods = new ArrayList<>();
        for (Method method : methods) {
            if (method.isAnnotationPresent(Test.class)) {
                annotatedMethods.add(method);
            }
        }

        annotatedMethods.sort((o1, o2) -> o2.getAnnotation(Test.class).priority()- (o1.getAnnotation(Test.class).priority()));

        for (Method o : methods) {
            if (o.isAnnotationPresent(BeforeSuite.class)) {
                if (!annotatedMethods.get(0).isAnnotationPresent(BeforeSuite.class)) {
                    annotatedMethods.add(0, o);
                } else throw new RuntimeException("Больше одного метода с аннотацией @BeforeSuite");
            } else if (o.isAnnotationPresent(AfterSuite.class)) {
                if (!annotatedMethods.get(annotatedMethods.size() - 1).isAnnotationPresent(AfterSuite.class)) {
                    annotatedMethods.add(o);
                } else throw new RuntimeException("Больше одного метода с аннотацией @AfterSuite");
            }
        }

        for (Method method : annotatedMethods) {
            method.invoke(testClass.getDeclaredConstructor().newInstance());
            if (method.isAnnotationPresent(Test.class)) {
                System.out.println(String.format("Это метод: %s. Его приоритет равен: %d.", method.getName(), method.getAnnotation(Test.class).priority()));
            }
        }
    }
}
