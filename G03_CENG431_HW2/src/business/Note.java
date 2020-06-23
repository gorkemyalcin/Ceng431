package business;

import java.util.Date;

import org.json.simple.JSONObject;

public class Note implements INote {
	private Integer id;
	private String title;
	private String content;
	private INoteState state;
	private Date date;

	public Note() {
		super();
	}

	public Note(Integer id, String title, String content, INoteState state, Date date) {
		super();
		this.id = id;
		this.title = title;
		this.content = content;
		this.state = state;
		this.date = date;
	}

	public String toString() {
		return "(" + id + ") " + title;
	}
	
	@Override
	public void printNoteInformation() {
		System.out.println("("+id +") " +content);
	}

	@Override
	public Integer getMaxId() {
		Integer maxId = id;
		maxId++;
		return maxId;
	}

	public void printNoteState() {
		System.out.println("Current state: " + state.toString());
	}

	@SuppressWarnings("unchecked")
	public JSONObject getJSON() {
		JSONObject noteJSON = new JSONObject();
		noteJSON.put("id", id);
		noteJSON.put("title", title);
		noteJSON.put("content", content);
		noteJSON.put("state", state.toString());
		noteJSON.put("date", date.toString());
		return noteJSON;
	}

	public void setState(INoteState state) {
		this.state = state;
	}

	public INoteState getState() {
		return state;
	}

	public Date getDate() {
		return date;
	}

	public Integer getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getContent() {
		return content;
	}

}
