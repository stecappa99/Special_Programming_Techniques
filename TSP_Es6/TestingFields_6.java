import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Proxy;
import java.util.Date;

public class TestingFields_6  implements ITest{
    private Double[] d;
    private Date dd;
    private int the_answer = 42;

    public TestingFields_6(int n, double val) {
        dd = new Date();
        d = new Double[n];
        for(int i=0; i<n; i++) d[i] = i*val;
    }

    public void setAnswer(int a) { the_answer = a; }
    public String message() { return "The answer is "+the_answer; }

    public static void main(String[] args) {
        ITest tVariable = (ITest) Proxy.newProxyInstance
        (
            TestingFields_6.class.getClassLoader(),
            TestingFields_6.class.getInterfaces(),
            new MyProxy(new TestingFields_6(7, 3.14))
        );

        tVariable.setAnswer(1);

    }

    public void printStatus() {

        for (Field f : this.getClass().getDeclaredFields())
        {
            try {
                if (f.getType().isArray())
                {
                    for (int i = 0; i<Array.getLength(f.get(this));i++)
                    {
                        System.out.println(f.getName() + "[" + i+ "]:" + Array.get(f.get(this), i));
                    }
                }
                else
                {
                    System.out.println(f.getName() + ":" + f.get(this));
                }

            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }
}
