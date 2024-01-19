import java.lang.reflect.InvocationTargetException;

public class CheckAppendTime {

    public static void main(String[] parArgs) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {

        StringBuilder tBuilder1 = new StringBuilder("Ciao");
        StringBuilder tBuilder2 = new StringBuilder("Ciao");
        int tLength = tBuilder2.length();

        tBuilder1.append(new String(" mondo!"));
        tBuilder2.insert(tLength, new String(" mondo!"));    }

}
