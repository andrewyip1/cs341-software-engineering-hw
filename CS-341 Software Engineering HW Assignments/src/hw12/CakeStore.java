package hw12;

public class CakeStore {
	
	private CakeFactory cakeFactory;
	
	public CakeStore (CakeFactory cakeFactory) {
		this.cakeFactory = cakeFactory;
	}
	
	public Cake onlineOrder (String type) {
		// PROCESS 1: COMPLETE THE ONLINE ORDER
		Cake cake = cakeFactory.orderCake(type);
		
		// PROCESS 2: PREPARATION OF THE CAKE
		cake.prepare();
		cake.bake();
		cake.box();
		
		return cake;
	}

}
