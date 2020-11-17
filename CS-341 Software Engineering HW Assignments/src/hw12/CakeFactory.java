package hw12;

public class CakeFactory {
	
	public Cake orderCake (String type) {
		if (type.equals("vanilla")) {
			return new VanillaCake();
		}
		
		else if (type.equals("chocolate")) {
			return new ChocolateCake();
		}
		
		else {
			return new LemonCake();
		}
	}

}
