package businessLayer;

import org.json.simple.JSONObject;

public class FinishReportCreator {

	@SuppressWarnings("unchecked")
	public static JSONObject createFinalReportJSONFile(FinishReport finishReport) {
		JSONObject finishReportJSON = new JSONObject();

		finishReportJSON.put("trackDifficulty", finishReport.getTrackDifficulty().toString());
		finishReportJSON.put("trackType", finishReport.getTrackType().toString());
		finishReportJSON.put("trackLength", finishReport.getTrackLength().toString());
		finishReportJSON.put("whyTheGameEnded", finishReport.getWhyTheGameEnded());
		finishReportJSON.put("totalAmountOfCollectedCurrencies", finishReport.getCollectedCurrencyAmount());
		finishReportJSON.put("totalAmountOfCollectedDiamonds", finishReport.getCollectedDiamondAmount());
		finishReportJSON.put("score", finishReport.getScore());
		return finishReportJSON;
	}
}
