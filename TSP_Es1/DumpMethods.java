import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;

public class DumpMethods {
    public static void main(String[] args) {
        Class<?> tClass;
        boolean tFirst;

        try
        {
            if (args.length == 0)
            {
                throw new ClassNotFoundException("Classe non specificata come parametro.");
            }

            tClass = Class.forName(args[0]);

            for (Method tMethod :tClass.getMethods())
            {
                tFirst = true;
                if (Modifier.isPublic(tMethod.getModifiers()) && !Modifier.isStatic(tMethod.getModifiers()))
                {

                    System.out.println(tMethod);
                    //System.out.print("(");
                    //if(tMethod.getParameterCount() > 0)
//                    {
//                        for (Parameter tParameter : tMethod.getParameters())
//                        {
//                            if (!tFirst)
//                                System.out.print(",");
//                            System.out.print(tParameter.toString());
//                            tFirst = false;
//                        }
//                    }
//                    System.out.print(")\n");
                }
            }
        }
        catch (ClassNotFoundException e) {
            System.err.println(e.getMessage());
        }
    }
}