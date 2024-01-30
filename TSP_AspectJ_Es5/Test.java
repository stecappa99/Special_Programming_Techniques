
public class Test {
    public static void main(String[] args) {
        fact(5);
    }
    static int fact(int x) {
        if (x == 0) {
            System.err.println("bottoming out");
            return 1;
        }
        else return x * fact(x - 1);
    }
}