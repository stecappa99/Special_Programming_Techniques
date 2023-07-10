public class MyClassLoader extends ClassLoader {

    private static int counter = 0;

    public MyClassLoader(ClassLoader parParent)
    {
        super(parParent);
    }

    public static int getCounter()
    {
        return counter;
    }

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        counter++;
        //System.out.println(counter);
        Class<?> tClass = super.loadClass(name);
        System.out.println(tClass.getName());
        return tClass;
    }
}
