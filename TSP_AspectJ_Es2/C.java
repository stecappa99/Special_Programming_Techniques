
public class C {
	
	public void c()
	{
		System.out.println("Chiamata a c");
		A x = new A();
		
		x.a();
	}

	public void ba()
	{
		System.out.println("Chiamata a ba");
		B x = new B();
		
		x.b();
	}
}
