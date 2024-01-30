aspect A {
    pointcut entry(int i): call(int fact(int)) && args(i);
    pointcut writing(): call(void println(String)) && ! within(A);
    
    before(int i): writing() && cflow(entry(i)) {
        System.err.println("Current arg is " + i);
    }
}