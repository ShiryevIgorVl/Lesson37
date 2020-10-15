public class MyTest {

    @Test (priority = 1)
    public static void test1(){
        System.out.println("test1");
    }

    @Test
    public static void test2(){
        System.out.println("testDefault");
    }

    @Test(priority = 10)
    public static void test3(){
        System.out.println("test10");
    }

    @AfterSuite
    public static void afterSuite(){
        System.out.println("afterSuite");
    }

    @BeforeSuite
    public static void beforeSuite(){
        System.out.println("beforeSuite");
    }

}


