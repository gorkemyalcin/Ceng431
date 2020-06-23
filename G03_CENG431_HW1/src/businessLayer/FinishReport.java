package businessLayer;

import businessLayer.enums.TrackDifficulty;
import businessLayer.enums.TrackType;

/**
 * A class that contains the final report contents.
 *
 */
public class FinishReport {
	
	private TrackDifficulty trackDifficulty;
	private TrackType trackType;
	private Integer trackLength;
	private String whyTheGameEnded;
	private Integer collectedCurrencyAmount;
	private Integer collectedDiamondAmount;
	private Integer score;

	public FinishReport() {
	}

	public FinishReport(TrackDifficulty trackDifficulty, TrackType trackType, Integer trackLength,
			String whyTheGameEnded, Integer collectedCurrencyAmount, Integer collectedDiamondAmount, Integer score) {
		this.trackDifficulty = trackDifficulty;
		this.trackType = trackType;
		this.trackLength = trackLength;
		this.whyTheGameEnded = whyTheGameEnded;
		this.collectedCurrencyAmount = collectedCurrencyAmount;
		this.collectedDiamondAmount = collectedDiamondAmount;
		this.score = score;
	}

	public TrackDifficulty getTrackDifficulty() {
		return trackDifficulty;
	}

	public TrackType getTrackType() {
		return trackType;
	}

	public Integer getTrackLength() {
		return trackLength;
	}

	public String getWhyTheGameEnded() {
		return whyTheGameEnded;
	}

	public Integer getCollectedCurrencyAmount() {
		return collectedCurrencyAmount;
	}

	public Integer getCollectedDiamondAmount() {
		return collectedDiamondAmount;
	}

	public Integer getScore() {
		return score;
	}
}
