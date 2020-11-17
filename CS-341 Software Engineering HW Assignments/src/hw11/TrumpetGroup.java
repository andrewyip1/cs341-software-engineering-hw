package hw11;

public class TrumpetGroup implements Observer{
	
	public void update (StateChange m) {
		System.out.println("Notification to Tubas: " + m.getMessageContent());
	}

}
