public class MainNestedCalls {

    public static void main(String[] args) {
        NestedCalls tNestedCall = new NestedCallsProxy();

        System.out.println("a(): " + tNestedCall.a());
        System.out.println("b(a()): " + tNestedCall.b(tNestedCall.a()));
        System.out.println("c(b(a())): " + tNestedCall.c(tNestedCall.b(tNestedCall.a())));

    }

}
