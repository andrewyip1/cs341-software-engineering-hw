package hw11;

public class MyMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// Objective: Test the communication between conductor and the music groups watching him/her.
		
		// TASK 1: CREATE THE TROMBONE, AND TUBA GROUPS
		TromboneGroup tromboneGp = new TromboneGroup();
		TrumpetGroup trumpetGp = new TrumpetGroup();
		TubaGroup tubaGp = new TubaGroup();
		
		// TASK 2: CREATE THE MUSIC CONDUCTOR
		MusicConductor conductor = new MusicConductor();
		
		// TASK 3: ATTACH THE TROMBONE AND TRUMPET GROUPS
		// TO THE CONDUCTOR SO THEY RECEIVE UPDATE NOTIFICATIONS
		conductor.attach(tromboneGp);
		conductor.attach(trumpetGp);
		
		// TASK 4: TEST THE NOTIFICATION UPDATE
		conductor.notifyUpdate(new StateChange("Appassionato"));
		
		// TASK 5: DETACH TROMBONE GROUP AND ATTACH THE TUBA GROUP
		// detach t1 and attach t3. t2 and t3 will receive an update
		conductor.detach(tromboneGp);
		conductor.attach(tubaGp);
		
		// TASK 6: TEST THAT TRUMPETS AND TUBAS RECEIVE UPDATE NOTIFICATIONS
		conductor.notifyUpdate(new StateChange("Crescendo"));

	}

}
