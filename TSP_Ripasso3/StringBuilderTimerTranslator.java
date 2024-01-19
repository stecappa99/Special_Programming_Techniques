import javassist.*;

import java.io.IOException;

public class StringBuilderTimerTranslator implements Translator {
    @Override
    public void start(ClassPool classPool) throws NotFoundException, CannotCompileException {
        CtClass tStringBuilderCtClass = classPool.get("java.lang.StringBuilder");

        CtMethod tAppendMethod = tStringBuilderCtClass.getDeclaredMethod("append", new CtClass[] { classPool.get("java.lang.String") });
        CtMethod tInsertMethod = tStringBuilderCtClass.getDeclaredMethod("insert", new CtClass[] { CtClass.intType, classPool.get("java.lang.String") });

        tAppendMethod.addLocalVariable("tActualTime", CtClass.longType);
        tInsertMethod.addLocalVariable("tActualTime", CtClass.longType);
        tAppendMethod.insertBefore("tActualTime = java.lang.System.currentTimeMillis();");
        tInsertMethod.insertBefore("tActualTime = java.lang.System.currentTimeMillis();");

        tAppendMethod.insertAfter("System.out.println(\"Tempo trascorso: \" + (java.lang.System.currentTimeMillis() - tActualTime));");
        tInsertMethod.insertAfter("System.out.println(\"Tempo trascorso: \" + (java.lang.System.currentTimeMillis() - tActualTime));");

        try {
            tStringBuilderCtClass.writeFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onLoad(ClassPool classPool, String s) throws NotFoundException, CannotCompileException {


    }
}
