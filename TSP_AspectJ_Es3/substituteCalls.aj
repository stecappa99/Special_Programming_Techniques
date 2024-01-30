
public aspect substituteCalls {

	int around(int b) : call(int *.*(int)) && args(b) {
		
		return proceed(proceed(b));
	}
	
}
