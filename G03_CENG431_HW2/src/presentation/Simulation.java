package presentation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;

import business.INote;
import business.INoteState;
import business.IncompleteState;
import business.Note;
import business.NoteGroup;
import business.PermanentState;
import dataAccessLayer.NoteGroupExporter;

public class Simulation {

	private NoteGroup mainNoteGroup;

	public Simulation() {
		this.mainNoteGroup = new NoteGroup(1, "Main Note Group", new ArrayList<INote>());
	}

	public void printMainMenu() {
		System.out.println("Main menu:");
		System.out.println("1. Create a note or a note group.");
		System.out.println("2. Go to the notes.");
		System.out.println("3. Change the state of a note");
		System.out.println("4. Export notes to a JSON file.");
		System.out.println("5. Reset the program.");
		System.out.println("6. Exit the program.\n");
	}

	private void printGoToTheNotesMenu() {
		System.out.println(
				"Enter the id of a note or a note group you want to see, if you enter nothing, all notes will be displayed\n");
	}

	private void printGoodbyeMessage() {
		System.out.println("Program will now exit. Goodbye!\n");
	}

	private void printResetMessage() {
		System.out.println("Program restarted itself, all the notes are cleared.\n");
	}

	private void printAddNoteMessage() {
		System.out.println(
				"Enter the note group id that will be used as a parent for creating your note or note group. If you enter nothing, the new item will be added to the main note group.\n");
	}

	private void printCreateNoteTitleMessage() {
		System.out.println("Please write your note title\n");
	}

	private void printCreateNoteContentMessage() {
		System.out.println("Please write your note content\n");
	}

	private void printNoteOrNoteGroupSelectionMessage() {
		System.out.println("Please select your item type:");
		System.out.println("1. Note Group");
		System.out.println("2. Note\n");
	}

	private void printCreateNoteStateMessage() {
		System.out.println("Please select your note state:");
		System.out.println("1. Incomplete");
		System.out.println("2. Permanent\n");
	}

	private void printCreateNoteGroupTitleMessage() {
		System.out.println("Please write your note group title\n");
	}

	private void printWrongNumberFormatMessage() {
		System.out.println("Please enter a number value for the note id input. Returning to main menu.\n");
	}

	private void printSelectNewState() {
		System.out.println("Select the state you want to change your notes state to.");
		System.out.println("1. Cancelled");
		System.out.println("2. Completed\n");
	}

	private void printEnterNoteIdMessage() {
		System.out.println("Enter the note id of the note you want to change its state.\\n");
	}

	public void start() {
		handleInputs();
	}

	public void handleInputs() {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			printMainMenu();
			String choice;
			try {
				choice = br.readLine();
				if (choice.equalsIgnoreCase("1")) {// Create a note or a note group

					printAddNoteMessage();
					String strId = br.readLine();// Choosing the note group that we want to add the note or note group
													// to.
					if (strId.equals("")) {// Default - main note group
						Integer groupId = 1;
						getFieldsAndAddINote(br, groupId);

					} else {// User wants to add note-note group to a specific note group
						Integer groupId = getIdFromString(strId);// The id of the note group to be added to.
						if (groupId != null) {
							getFieldsAndAddINote(br, groupId);
						}
					}
				} else if (choice.equalsIgnoreCase("2")) {// Go to the notes.
					printGoToTheNotesMenu();
					String strId = br.readLine();// Getting the id of the note-note group to be printed
					printWantedNotes(strId);
				} else if (choice.equalsIgnoreCase("3")) {
					printEnterNoteIdMessage();
					String noteIdString = br.readLine();
					handleNoteStateChangeRequest(noteIdString, br);
				} else if (choice.equalsIgnoreCase("4")) {
					NoteGroupExporter.exportNoteGroupJSON(mainNoteGroup);
				} else if (choice.equalsIgnoreCase("5")) {
					resetTheProgram();
					printResetMessage();
				} else if (choice.equalsIgnoreCase("6")) {
					printGoodbyeMessage();
					break;
				} else {
					System.out.println("Please choose an id from the main menu.\n");
				}
			} catch (IOException e) {
				System.out.println("Error occured while reading from console.\n");
			}
		}
	}

	private void handleNoteStateChangeRequest(String noteIdString, BufferedReader br) throws IOException {
		Integer noteId = getIdFromString(noteIdString);
		if (noteId != null) {
			Note note = (Note) mainNoteGroup.findNoteWithNoteId(noteId);
			if (note != null) {
				note.printNoteInformation();
				note.printNoteState();
				printSelectNewState();
				String newState = br.readLine();
				handleNoteStateChange(newState, note);
			} else {
				System.out.println("No note can be found with id: " + noteId + ". Returning to main menu.\n");
			}
		}
	}

	private void handleNoteStateChange(String newState, Note note) {
		if (newState.equalsIgnoreCase("1")) {
			note.getState().onCancelled(note);
		} else if (newState.equalsIgnoreCase("2")) {
			note.getState().onCompleted(note);
		} else {
			System.out.println("Please choose an id from the list. Returning to main menu.\n");
		}
	}

	private void printWantedNotes(String strId) {
		if (strId.equals("")) {// Entering an empty string or 1 will return all the items in the main note
			// group.
			mainNoteGroup.printNoteInformation();
		} else {
			Integer intId = getIdFromString(strId);// User enters the wanted note-note groups id.
			if (intId != null) {
				mainNoteGroup.printNoteInformation(intId);// Prints the wanted content.
			}
		}
	}

	private void getFieldsAndAddINote(BufferedReader br, Integer groupId) throws IOException {
		Integer generatedItemId = mainNoteGroup.getMaxId();

		printNoteOrNoteGroupSelectionMessage();
		String itemSelection = br.readLine();// Choosing between note or a note group.

		addINote(itemSelection, groupId, generatedItemId, br);
	}

	private void addINote(String itemSelection, Integer groupId, Integer generatedItemId, BufferedReader br)
			throws IOException {
		if (itemSelection.equalsIgnoreCase("1")) {// Note group
			createAndAddNoteGroup(groupId, generatedItemId, br);// Creates a note group object and adds
																// it.
		} else if (itemSelection.equalsIgnoreCase("2")) {// Note
			createAndAddNote(generatedItemId, br, groupId);// Creates a note object and adds it.
		}
	}

	private Integer getIdFromString(String noteIdString) {
		try {
			return Integer.parseInt(noteIdString);
		} catch (NumberFormatException e) {
			printWrongNumberFormatMessage();
		}
		return null;
	}

	private void resetTheProgram() {
		mainNoteGroup.getNotes().clear();
	}

	private void createAndAddNoteGroup(Integer groupId, Integer generatedItemId, BufferedReader br) throws IOException {
		mainNoteGroup.addINoteToASpecificGroup(groupId, createNoteGroup(generatedItemId, br));
	}

	private void createAndAddNote(Integer createdItemId, BufferedReader br, Integer groupId) throws IOException {
		Note note = createNote(createdItemId, br);
		if (note.getState() == null) {
			System.out.println("Please enter an id from the state list. Returning to main menu.\n");
		} else {
			mainNoteGroup.addINoteToASpecificGroup(groupId, note);
		}
	}

	private NoteGroup createNoteGroup(Integer generatedItemId, BufferedReader br) throws IOException {
		printCreateNoteGroupTitleMessage();
		String title = br.readLine();
		return new NoteGroup(generatedItemId, title, new ArrayList<INote>());
	}

	private Note createNote(Integer generatedItemId, BufferedReader br) throws IOException {
		printCreateNoteTitleMessage();
		String title = br.readLine();
		printCreateNoteContentMessage();
		String content = br.readLine();
		printCreateNoteStateMessage();
		String stateId = br.readLine();
		INoteState state = translateStateIdToState(stateId);
		return new Note(generatedItemId, title, content, state, new Date());
	}

	private INoteState translateStateIdToState(String stateId) {
		if (stateId.equalsIgnoreCase("1")) {
			return new IncompleteState();
		} else if (stateId.equalsIgnoreCase("2")) {
			return new PermanentState();
		}
		return null;

	}

	public NoteGroup getMainNoteGroup() {
		return mainNoteGroup;
	}

	public void setMainNoteGroup(NoteGroup mainNoteGroup) {
		this.mainNoteGroup = mainNoteGroup;
	}
}
