import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class MyProxy implements InvocationHandler {

    ITest pObj;

    public MyProxy(ITest parObj)
    {
        this.pObj = parObj;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) {
        try {
            this.pObj.printStatus();
            Object tResult = method.invoke(this.pObj, args);
            this.pObj.printStatus();
            return tResult;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }

    }
}
