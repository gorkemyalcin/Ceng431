package dataAccessLayer;

import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONObject;

public class FinishReportFileWriter {

	public static void writeReportToFile(JSONObject finishReport) {
		try (FileWriter file = new FileWriter("endResult.json")) {

			file.write(finishReport.toJSONString());
			file.flush();

		} catch (IOException e) {
			System.out.println("Error occured while writing to the file.");
		}
	}
}
