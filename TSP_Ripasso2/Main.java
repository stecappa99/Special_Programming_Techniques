import javassist.*;

import java.io.IOException;
import java.time.Period;

public class Main {
    public static void main(String[] parArgs) throws Throwable {

        ClassPool tPool = ClassPool.getDefault();
        Loader tLoader = new Loader(tPool);
        CtClass tCtPersonClass = tPool.makeClass("Person");
        CtClass tStringClass = tPool.get("java.lang.String");
        CtClass tDateClass = tPool.get("java.time.LocalDate");
        CtClass tIntClass = tPool.get("int");

        CtField tNameField = new CtField(tStringClass, "name", tCtPersonClass);
        CtField tDateField = new CtField(tDateClass, "birthDate", tCtPersonClass);

        tCtPersonClass.addField(tNameField);
        tCtPersonClass.addField(tDateField);
        tCtPersonClass.addMethod(CtNewMethod.getter("getName", tNameField));
        tCtPersonClass.addMethod(CtNewMethod.getter("getBirthDate", tDateField));
        CtConstructor tConstructor = new CtConstructor(new CtClass[]{ tStringClass, tDateClass }, tCtPersonClass);
        tConstructor.setBody("{ name = $1; birthDate = $2; }");
        tCtPersonClass.addConstructor(tConstructor);

        CtMethod tNewAgeMethod = new CtMethod(tIntClass, "getAge", new CtClass[] { }, tCtPersonClass );
        tNewAgeMethod.setBody("{ return java.time.Period.between(birthDate, java.time.LocalDate.now()).getYears(); }");
        tCtPersonClass.addMethod(tNewAgeMethod);

        tCtPersonClass.writeFile();
        Class tClass = tLoader.loadClass("Person");
        tLoader.run("TestPerson", parArgs);
        //System.out.println(tClass);
        //tCtPersonClass.writeFile();


    }
}
