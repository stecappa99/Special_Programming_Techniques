import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class NestedCallsProxy extends NestedCalls implements InvocationHandler {

    private final NestedCallsI proxy;
    private int counter = 0;

    public NestedCallsProxy()
    {
        this.proxy = (NestedCallsI) Proxy.newProxyInstance
        (this.getClass().getClassLoader(), new Class[] { NestedCallsI.class  }, this);
    }

    @Override
    public int a() { return proxy.a(); }

    @Override
    public int b(int a) { return proxy.b(a); }

    @Override
    public int c(int a) { return proxy.c(a); }

    @Override
    public Object invoke(Object proxy, Method m, Object[] args) {

        Object result = null;

        try {
            final Method superm = NestedCalls.class.getDeclaredMethod(m.getName(), m.getParameterTypes());
            counter++;
            System.out.println("#".repeat(counter) + " " + m.getName());
            final MethodHandle mh = MethodHandles.lookup().unreflectSpecial(superm, this.getClass());
            result = mh.bindTo(this).invokeWithArguments(args);
            counter--;
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }

        return result;
    }
}
