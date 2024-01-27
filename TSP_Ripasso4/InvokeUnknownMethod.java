import javassist.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

public class InvokeUnknownMethod {

    private static CtClass pStringClass;

    public static Optional<CtClass> getCtClassFromString(String parValue) {

        CtClass tCtClass;

        if(parValue.matches("^\\d*[.]\\d+$")){

            tCtClass = CtClass.doubleType;
        }
        else if (parValue.matches("^\\d+$"))
        {
            tCtClass = CtClass.intType;
        }
        else
        {
            try {
                tCtClass = ClassPool.getDefault().get("java.lang.String");
            } catch (NotFoundException e) {
                return Optional.empty();
            }
        }

        return Optional.of(tCtClass);
    }

    public static void main(String[] parArgs) throws Throwable {

        ClassPool tClassPool = ClassPool.getDefault();
        Loader tLoader = new Loader(tClassPool);
        pStringClass = tClassPool.get("java.lang.String");

        if (parArgs.length > 2){
            CtClass tClassMethod = tClassPool.get(parArgs[0]);
            CtClass tReturnClass = null;
            String tBody = null;
            String[] tStringParameters = Arrays.copyOfRange(parArgs, 2, parArgs.length);
            CtClass[] tParameters =  Arrays
                                        .stream(tStringParameters)
                                        .map(InvokeUnknownMethod::getCtClassFromString)
                                        .filter(Optional::isPresent)
                                        .map(Optional::get)
                                        .toArray(CtClass[]::new);

            for (int iClass = 0; iClass < tParameters.length; iClass++)
            {
                if(tParameters[iClass] == pStringClass)
                    tStringParameters[iClass] = "\" "+ tStringParameters[iClass] +" \"";

                if (tReturnClass == null)
                    tReturnClass = tParameters[iClass];
                else if (tReturnClass != pStringClass && tParameters[iClass] == pStringClass)
                    tReturnClass = pStringClass;
                else if(tReturnClass != CtClass.doubleType && tParameters[iClass] == CtClass.doubleType)
                    tReturnClass = CtClass.doubleType;
            }

            switch (parArgs[1])
            {
                case "add": tBody = "return $1 + $2;"; break;
                case "mul": tBody = "return $1 * $2;"; break;
                case "div": tBody = "return $1 / $2;"; break;
                case "neg": tBody = "return -$1;"; break;
            }

            CtMethod tTypedMethod = CtNewMethod.make(tReturnClass, "Custom" + parArgs[1], tParameters, new CtClass[] {}, tBody, tClassMethod);

            tClassMethod.addMethod(tTypedMethod);
            tClassMethod.writeFile();

            String tMainBody = "java.lang.System.out.println(";
            tMainBody += "new " + parArgs[0] + "().Custom" + parArgs[1] + "(" + String.join(", ", tStringParameters) + ")";
            tMainBody += ");";
            CtClass tMainClass = tClassPool.makeClass("MainClass");
            tMainClass.addMethod(CtNewMethod.make("public static void main(String[] parArgs) { " + tMainBody +" }", tMainClass));
            tMainClass.writeFile();

            tLoader.run("MainClass", new String[]{ });
        }
    }

}


