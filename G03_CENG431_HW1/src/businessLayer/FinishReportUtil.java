package businessLayer;

import org.json.simple.JSONObject;

import dataAccessLayer.FinishReportFileWriter;

public class FinishReportUtil {

	public static void conclude(FinishReport finishReport) {
		JSONObject finishReportJSON = FinishReportCreator.createFinalReportJSONFile(finishReport);
		FinishReportFileWriter.writeReportToFile(finishReportJSON);
	}
}
