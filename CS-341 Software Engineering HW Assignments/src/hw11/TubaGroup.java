package hw11;

public class TubaGroup implements Observer{
	
	public void update (StateChange m) {
		System.out.println("Notification to Trombones: " + m.getMessageContent());
	}

}
