package business;

public class CompletedState implements INoteState {

	private static final String MESSAGE = "Can not change the state of a completed note.";

	@Override
	public void onCancelled(Note note) {
		System.out.println(MESSAGE);
	}

	@Override
	public void onCompleted(Note note) {
		System.out.println(MESSAGE);
	}

	public String toString() {
		return "Completed";
	}
}
