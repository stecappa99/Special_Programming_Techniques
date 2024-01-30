
public aspect removeCall {

	pointcut callToA(): call(* A.*(..));
	
	pointcut targetCall():
					callToA()
					&&
					!within(C)
					&&
					withincode(* *.*(..));
		
	void around(): targetCall() {
		System.out.println("Sto non facendo niente");
	}
	
}
