
import java.util.Arrays;
import java.util.HashMap;

public aspect MultiTriggerManager perthis(execution(void *.*(..))) {

	private HashMap<String, Object[]> pMap = new HashMap<String, Object[]>();
	
	void around() : execution(void DummyClass.*(..)){
		
		Object[] tArgs = thisJoinPoint.getArgs();
		String tMethodName = thisJoinPoint.getSignature().toString();
		
		System.out.println(tMethodName);
			
		if(this.pMap.containsKey(tMethodName)) {
			for(int iArg = 0; iArg < tArgs.length; iArg++) {
				try {
					
					
					
					this.pMap.remove(tMethodName);
					
					proceed();
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}		
		}
		else
		{
			this.pMap.put(tMethodName, tArgs);
		}
		
		
	}
	
}
