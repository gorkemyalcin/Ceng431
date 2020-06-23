package business;

public interface INoteState {
	
	public abstract void onCancelled(Note note);

	public abstract void onCompleted(Note note);
}
