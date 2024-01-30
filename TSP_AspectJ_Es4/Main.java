
public class Main {

	public static void main(String[] args) {
		
		RA tRA1 = new RA();
		RA tRA2 = new RA();

		RB tRB1 =new RB();
		RB tRB2 =new RB();
		
		RC tRC1 =new RC();
		RC tRC2 =new RC();
		
		tRA1.destroy();
		 
		RB tRB3 = new RB();
		tRB3.destroy();
		
		new RB();
		
		tRC1.destroy();
		
		tRB1.destroy();
		tRB2.destroy();
		
		new RA();
		
		new RB();
	}

}
