import java.lang.reflect.*;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

public class InvokeUnknownMethodReflection {

    public static LinkedHashMap<String, Class> patterns = new LinkedHashMap<>()
    {
        {
            put("^[+-]?\\d*\\.\\d*$", double.class);
            put("^[+-]?\\d*$", int.class);
            put("^.*$", String.class);
        }
    };

    public static Class[] recognizeClass(String[] parArgs)
    {
        return Arrays.stream(parArgs)
                .map(e -> patterns
                            .entrySet()
                            .stream()
                            .map(p -> match(p, e))
                            .filter(v -> v.isPresent())
                            .map(p -> p.get())
                            .findFirst()
                            .get())
                .toArray(Class[]::new);
    }

    public static Optional<Class> match(Map.Entry<String, Class> p, String e)
    {
        if (Pattern.matches(p.getKey(), e)) return Optional.of(p.getValue());
        else return Optional.empty();
    }

    public static Object[] convertArgs(Class[] parClasses, String[] parArgs)
    {
        return IntStream.range(0, parArgs.length)
                    .mapToObj(i -> {
                        if (parClasses[i] == double.class)
                            return Double.valueOf(parArgs[i]);
                        else if (parClasses[i] == int.class) {
                            return Integer.valueOf(parArgs[i]);
                        }
                        else {
                            return parArgs[i];
                        }
                    })
                    .toArray();
    }

    public static void main(String[] args)
    {
        Class tClass;
        Class[] tTypes;
        String[] tArguments;
        try {
            if (args.length < 2)
            {
                System.err.println("Parametri non validi!");
                System.exit(1);
            }
            tArguments = Arrays.copyOfRange(args, 2, args.length);
            tTypes = recognizeClass(tArguments);
            tClass = Class.forName(args[0]);
            Object tObject = tClass.newInstance();
            Method tMethod = tClass.getDeclaredMethod(args[1], tTypes);
            System.out.println("Result is " + tMethod.invoke(tObject, convertArgs(tTypes, tArguments)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
