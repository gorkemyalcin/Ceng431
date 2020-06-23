package business;

public class IncompleteState implements INoteState {

	@Override
	public void onCancelled(Note note) {
		note.setState(new CancelledState());
		System.out.println("State changed to cancelled.");
	}

	@Override
	public void onCompleted(Note note) {
		note.setState(new CompletedState());
		System.out.println("State changed to completed.");
	}

	public String toString() {
		return "Incompleted";
	}
}
