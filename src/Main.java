import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;

public class Main {



    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {
        Class myTest = MyTest.class;
        ArrayList<Method> md = new ArrayList<>();
      Method[] methods = MyTest.class.getDeclaredMethods();

        for (Method m : methods) {
            if (m.isAnnotationPresent(Test.class)){
                md.add(m);
            }
        }
        md.sort(Comparator.comparingInt((Method i) -> i.getAnnotation(Test.class).priority()).reversed());



        for (Method m : methods) {
            if (m.isAnnotationPresent(BeforeSuite.class)) {
                if ( md.get(0).isAnnotationPresent(BeforeSuite.class) && md.size() > 0) {
                    throw new RuntimeException("более одного метода с аннотацией @BeforeSuite");
                }
                md.add(0, m);
            }
          }



        for (Method m : methods) {
            if (m.isAnnotationPresent(AfterSuite.class)) {
                if (md.size()-1 > 0 && md.get(md.size()-1).isAnnotationPresent(AfterSuite.class)) {
                    throw new RuntimeException("более одного метода с аннотацией @AfterSuite");
                }
                md.add(md.size(), m);
            }

        }


        for (Method m : md) {
            m.invoke(null);
        }

    }







}
