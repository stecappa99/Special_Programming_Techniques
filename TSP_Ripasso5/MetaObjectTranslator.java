import javassist.*;

import java.util.Arrays;


public class MetaObjectTranslator implements Translator{

    @Override
    public void start(ClassPool classPool) throws NotFoundException, CannotCompileException {

        CtClass tClass = classPool.get("TestingFieldsBytecode");
        String tStatusPrint = "";

        for (CtField tField : tClass.getDeclaredFields())
        {
            if(tField.getType().isArray())
            {
                //java.lang.System.out.println("Array "+ tField.getName() +" = [" + java.lang.String.join(" - ", java.util.Arrays.stream(x).map(java.lang.Object::toString).toArray(java.lang.String[]::new)) + "]");
                //java.lang.System.out.println("Array d = [" + java.lang.String.join(" - ", java.util.Arrays.stream(d).map(java.lang.Object::toString).toArray(java.lang.String[]::new)) + "]");
                tStatusPrint += "java.lang.System.out.println(\"Array "+ tField.getName() +" = \" + java.util.Arrays.toString("+ tField.getName() +"));";

            }
            else if(tField.getType().isPrimitive())
            {
                tStatusPrint += "java.lang.System.out.println(\"Primitivo " + tField.getName() +" = \" + " + tField.getName() + ");";
            }
            else
            {
                tStatusPrint += "java.lang.System.out.println(\""+ tField.getType().getName() + " " + tField.getName() +" = \" + " + tField.getName() + ".toString());";
            }
        }


        for (CtMethod tMethod : tClass.getDeclaredMethods())
        {
            if(!Modifier.isStatic(tMethod.getModifiers())) {
                tMethod.insertBefore(tStatusPrint);
                tMethod.insertAfter(tStatusPrint);
            }
        }

    }

    @Override
    public void onLoad(ClassPool parClassPool, String s) throws NotFoundException, CannotCompileException {


    }

    public static void main(String[] parArgs) throws Throwable {

        ClassPool tClassPool = ClassPool.getDefault();
        Loader tLoader = new Loader(tClassPool);

        tLoader.addTranslator(tClassPool, new MetaObjectTranslator());

        tLoader.run("TestClass", parArgs);
    }
}
