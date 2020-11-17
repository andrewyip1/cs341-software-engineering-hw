package hw12;

public class AppDriver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		CakeFactory cakeFactory = new CakeFactory();
		CakeStore store = new CakeStore(cakeFactory);
		
		Cake cake1 = store.onlineOrder("lemon");
		Cake cake2 = store.onlineOrder("chocolate");
		Cake cake3 = store.onlineOrder("vanilla");

		System.out.println("Completed order: " + cake1.getName());
		System.out.println("Completed order: " + cake2.getName());
		System.out.println("Completed order: " + cake3.getName());
		
	}

}
