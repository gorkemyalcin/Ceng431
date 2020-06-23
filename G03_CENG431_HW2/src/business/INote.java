package business;

import org.json.simple.JSONObject;

public interface INote {

	public void printNoteInformation();

	public Integer getId();

	public String getTitle();

	public Integer getMaxId();
	
	public JSONObject getJSON();
}
