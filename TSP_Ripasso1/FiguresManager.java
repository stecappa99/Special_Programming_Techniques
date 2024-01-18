import javassist.*;
import javassist.bytecode.MethodInfo;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.Objects;

public class FiguresManager {

    public static void main(String[] parArgs) throws NotFoundException, CannotCompileException, IOException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, ClassNotFoundException {
        ClassPool tPool = ClassPool.getDefault();
        Loader tLoader = new Loader(tPool);

        CtClass tPointClass = tPool.makeClass("Point");
        CtField tFieldX = CtField.make("int x = 0;", tPointClass);
        CtField tFieldY = CtField.make("int y = 0;", tPointClass);
        tPointClass.addField(tFieldX);
        tPointClass.addField(tFieldY);
        tPointClass.addMethod(CtNewMethod.getter("getX", tFieldX));
        tPointClass.addMethod(CtNewMethod.getter("getY", tFieldY));
        tPointClass.addMethod(CtNewMethod.setter("setX", tFieldX));
        tPointClass.addMethod(CtNewMethod.setter("setY", tFieldY));
        CtConstructor tConstructor = new CtConstructor(new CtClass[]{tPool.get("int"), tPool.get("int")}, tPointClass);
        tConstructor.setBody("{x = $1; y = $2;}");
        tPointClass.addConstructor(tConstructor);
        tPointClass.writeFile();

        Class<?> a = tLoader.loadClass("Point");

        var x = a.getConstructor(new Class[] { int.class, int.class }).newInstance(new Object[] { 2, 3 });
        System.out.println(x.getClass().getMethod("getX", new Class[] { }).invoke(x, new Object[]{ }));
        System.out.println(x.getClass().getMethod("getY", new Class[] { }).invoke(x, new Object[]{ }));
    }

}
