package dataAccessLayer;

import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONObject;

import business.NoteGroup;

public class NoteGroupExporter {

	public static void exportNoteGroupJSON(NoteGroup mainNoteGroup) {
		JSONObject noteGroupJSON = mainNoteGroup.getJSON();
		try (FileWriter myWriter = new FileWriter("notes.json");) {
			myWriter.write(noteGroupJSON.toString());
			System.out.println("Successfully exported to the notes.txt");
		} catch (IOException e) {
			System.out.println("An error occurred while writing to the file.");
		}
	}

}
