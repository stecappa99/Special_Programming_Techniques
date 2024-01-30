import java.util.Arrays;
import java.util.stream.Stream;

public class DummyClass {
	public void m1(String s) { 
		System.out.println("I got " + s);
	} 
	public void m2(int i) { 
		System.out.println("I got: " + i);
	}
	
	public void m2(int i, String s) {
		System.out.println("I got: " + i + " and: " + s);
	}
		
	public static void main (String[] args) {
		DummyClass o1 = new DummyClass(), o2 = new DummyClass();
		
		o1.m1("Walter ");
		o2.m1("Cazzola");
		o2.m1(", Walter");
		o1.m2(7);
		o2.m2(7, "Walter ");
		o1.m2(25);
		o2.m2(69);
	}
	
	
}