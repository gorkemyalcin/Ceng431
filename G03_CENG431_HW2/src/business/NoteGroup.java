package business;

import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class NoteGroup implements INote {

	private Integer id;
	private String title;
	private List<INote> notes;

	public NoteGroup(Integer id, String title, List<INote> notes) {
		super();
		this.id = id;
		this.title = title;
		this.notes = notes;
	}

	public NoteGroup() {
		super();
	}

	public String toString() {
		return "(" + id + ") -" + title + "-";
	}

	@Override
	public void printNoteInformation() {
		System.out.println(toString());
		for (INote note : notes) {
			note.printNoteInformation();
		}
	}

	public void printNoteInformation(Integer noteId) {
		if (noteId.equals(id)) {
			printNoteInformation();
		}
		for (INote note : notes) {
			if (note.getId().equals(noteId)) {
				note.printNoteInformation();
			}
		}
	}

	public INote findNoteWithNoteId(Integer noteId) {
		for (INote note : notes) {
			if (note.getId().equals(noteId) && note instanceof Note) {
				return note;
			}
		}
		return null;
	}

	public void addINote(INote note) {
		notes.add(note);
	}

	public void removeINote(INote note) {
		notes.remove(note);
	}

	@SuppressWarnings("unchecked")
	public JSONObject getJSON() {
		JSONObject noteGroupJSON = new JSONObject();
		noteGroupJSON.put("id", id);
		noteGroupJSON.put("title", title);
		JSONArray array = new JSONArray();
		for (INote note : notes) {
			array.add(note.getJSON());
		}
		noteGroupJSON.put("items", array);
		return noteGroupJSON;
	}

	public Integer getMaxId() {
		Integer maxId = id;
		for (INote note : notes) {
			if (note.getMaxId() > maxId) {
				maxId = note.getMaxId();
			}
		}
		maxId++;
		return maxId;
	}

	public void addINoteToASpecificGroup(Integer groupId, INote noteToBeAdded) {
		if (groupId.equals(id)) {
			notes.add(noteToBeAdded);
			System.out.println("Succesfully added the new note\n");
		} else {
			for (INote note : notes) {
				if (note.getId().equals(groupId)) {
					if (note instanceof NoteGroup) {
						NoteGroup noteGroup = (NoteGroup) note;
						noteGroup.addINote(noteToBeAdded);
						System.out.println("Succesfully added the new note\n");
					} else {
						System.out.println("Can not add an item to a note.");
					}
					break;
				}
				System.out.println("Couldn't find a note group with the id: " + groupId);
			}
		}

	}

	public Integer getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public List<INote> getNotes() {
		return notes;
	}

}
