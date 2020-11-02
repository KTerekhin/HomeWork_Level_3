public class TestClass {

    @Test(priority = 10)
    public void doMethod1() {}

    @Test(priority = 9)
    public void doMethod2() {}

    @Test(priority = 8)
    public void doMethod3() {}

    @Test(priority = 2)
    public void doMethod4() {}

    @Test(priority = 5)
    public void doMethod5() {}

    @BeforeSuite
    public void doMethod() {
        System.out.println("| @BeforeSuite | Это самый первый метод");
    }

    @AfterSuite
    public void doLastMethod() {
        System.out.println("| @AfterSuite | Это самый последний метод");
    }
}
