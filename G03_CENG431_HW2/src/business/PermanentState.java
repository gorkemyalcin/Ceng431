package business;

public class PermanentState implements INoteState {

	@Override
	public void onCancelled(Note note) {
		note.setState(new CancelledState());
		System.out.println("State changed to cancelled.");
	}

	@Override
	public void onCompleted(Note note) {
		System.out.println("Can not change the state to completed from a permanent state.");
	}

	public String toString() {
		return "Permanent";
	}
}
