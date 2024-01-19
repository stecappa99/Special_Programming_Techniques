import javassist.ClassPool;
import javassist.Loader;

public class CheckAppendTimeMain {

    public static void main(String[] parArgs) throws Throwable {
        ClassPool tClassPool = ClassPool.getDefault();
        Loader tLoader = new Loader(tClassPool);

        tLoader.addTranslator(tClassPool, new StringBuilderTimerTranslator());

        tLoader.run("CheckAppendTime", parArgs);
    }

}





