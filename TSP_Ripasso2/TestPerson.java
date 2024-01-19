import java.lang.reflect.InvocationTargetException;
import java.time.LocalDate;

public class TestPerson {

    public static void main(String[] parArgs) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {

        Class tClass = Class.forName("Person");
        Object tPerson = tClass.getConstructor(new Class[]{ String.class, LocalDate.class }).newInstance(new Object[]{ "Stefano", LocalDate.of(1999, 2, 10) } );

        System.out.println(tClass.getMethod("getName").invoke(tPerson));
        System.out.println(tClass.getMethod("getBirthDate").invoke(tPerson));
        System.out.println(tClass.getMethod("getAge").invoke(tPerson));
    }

}
