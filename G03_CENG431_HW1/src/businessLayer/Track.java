package businessLayer;

import java.util.Random;

import businessLayer.enums.TrackDifficulty;
import businessLayer.enums.TrackType;

public class Track {

	private TrackDifficulty trackDifficulty;
	private TrackType trackType;
	private Integer trackLength;

	public Track() {

	}

	public Track(TrackType trackType, Integer trackLength, TrackDifficulty trackDifficulty) {
		super();
		this.trackType = trackType;
		this.trackLength = trackLength;
		this.trackDifficulty = trackDifficulty;
	}

	/**
	 * For a 50% chance, initializes the track type as jungle or mountain.
	 * Sets the trackLength between the minTrackLength and maxTrackLength
	 * For a random chance, sets the trackDifficulty as easy, moderate, hard or extreme.
	 */
	public void initialize(Integer minTrackLength, Integer maxTrackLength) {
		Random random = new Random();
		this.trackType = random.nextBoolean() ? TrackType.JUNGLE : TrackType.MOUNTAIN;
		this.trackLength = getRandomDoubleBetweenRange(minTrackLength, maxTrackLength);

		Double probabilityOfTrackDifficulty = random.nextDouble();
		TrackDifficulty difficulty;

		if (probabilityOfTrackDifficulty < 0.25) {
			difficulty = TrackDifficulty.EASY;
		} else if (probabilityOfTrackDifficulty < 0.5) {
			difficulty = TrackDifficulty.MODERATE;
		} else if (probabilityOfTrackDifficulty < 0.75) {
			difficulty = TrackDifficulty.HARD;
		} else {
			difficulty = TrackDifficulty.EXPERT;
		}

		this.trackDifficulty = difficulty;
	}

	private Integer getRandomDoubleBetweenRange(Integer min, Integer max) {
		return (int) Math.round((Math.random() * ((max - min) + 1)) + min);
	}

	public TrackType getTrackType() {
		return trackType;
	}

	public Integer getTrackLength() {
		return trackLength;
	}

	public TrackDifficulty getTrackDifficulty() {
		return trackDifficulty;
	}

}
