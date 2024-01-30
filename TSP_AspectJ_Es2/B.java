
public class B {
	
	public void b()
	{
		System.out.println("Chiamata a b");
		A x = new A();
		
		x.a();
	}
	
	public void bc()
	{
		System.out.println("Chiamata a bc");
		C x = new C();
		
		x.c();
	}
	
	public void da()
	{
		new D().d();
	}
}
