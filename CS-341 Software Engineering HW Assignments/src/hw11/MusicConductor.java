package hw11;
import java.util.ArrayList;
import java.util.List;

public class MusicConductor implements Subject{
	
	private List<Observer> observers = new ArrayList<>();
	
	public void attach (Observer o) {
		observers.add(o);
	}
	
	public void detach (Observer o) {
		observers.remove(o);
	}
	
	public void notifyUpdate (StateChange sc) {
		for (Observer o: observers) {
			o.update(sc);
		}
	}

}
