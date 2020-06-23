package businessLayer;

public class Event<T> {

	private Integer eventLocation;
	private T type;

	public Event() {
	}

	public Event(Integer eventLocation, T type) {
		this.setEventLocation(eventLocation);
		this.type = type;
	}

	public Integer getEventLocation() {
		return eventLocation;
	}

	public void setEventLocation(Integer eventLocation) {
		this.eventLocation = eventLocation;
	}

	public T getType() {
		return type;
	}

}
