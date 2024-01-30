import java.util.HashMap;

public aspect ResourceManager implements ResourcePool {

	private HashMap<String, Resource> pResources = new HashMap<String, Resource>();
	
	@Override
	public Resource getResource(String type) {
		
		System.out.println("*** I'm pooling a " + type + " instance");
		return this.pResources.get(type);
	}

	@Override
	public void releaseResource(String type, Resource r) {
		
		System.out.println("*** I'm storing a " + type + " instance");
		this.pResources.put(type, r);
	}

	Object around(Resource parResource) : execution(Resource+.new(..)) && this(parResource){
		
		String tType = parResource.getClass().getName();
		
		if(this.pResources.containsKey(tType))
		{
			return this.getResource(tType);
		}
		
		return proceed(parResource);
	}
	
	before(Resource parResource) : execution(void Resource+.destroy()) && this(parResource){
		
		this.releaseResource(parResource.getClass().getName(), parResource);
	}
	
}
