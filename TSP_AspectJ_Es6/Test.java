public class Test {
    public static void main(String[] args) {
        foo();
    }
    static void foo() {
        hoo();
    }
    static void goo() {
    	System.out.println("hi");
    }
    static void hoo() {
    	goo();
    }
    
}