package businessLayer.enums;

public enum TrackDifficulty {
	EASY(1), MODERATE(2), HARD(3), EXPERT(4);

	private Integer trackDifficultyBonus;

	public Integer getTrackDifficultyBonus() {
		return this.trackDifficultyBonus;
	}

	TrackDifficulty(Integer trackDifficultyBonus) {
		this.trackDifficultyBonus = trackDifficultyBonus;
	}
}
