import java.lang.reflect.Field;
import java.util.Date;

public class TestingFields {
    private Double d[];
    private Date dd;
    public static final int i = 42;
    protected static String s = "testing ...";

    public TestingFields(int n, double val) {
        dd = new Date();
        d = new Double[n];
        for(int i=0; i<n; i++) d[i] = i*val;
    }

    public static void main(String[] parArgs)
    {
        TestingFields tTestingField = new TestingFields(7, 3.14);

        Class tClass = tTestingField.getClass();

        try {
            Field tField = tClass.getDeclaredField("s");
            tField.set(tTestingField, "testing ... passed!!!");

            System.out.println(tField.get(tTestingField));
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }

    }
}
