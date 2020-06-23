package businessLayer;

import java.util.Random;

import businessLayer.enums.CurrencyType;

public class Player {

	private boolean hasMagnet;
	private Integer location;
	private Integer score;
	private Integer trackDifficultyBonus;
	private Integer collectedCurrencyAmount;
	private Integer collectedDiamondAmount;

	public Player() {
	}

	public Player(boolean hasMagnet, Integer location, Integer score, Integer trackDifficultyBonus,
			Integer collectedCurrencyAmount, Integer collectedDiamondAmount) {
		super();
		this.hasMagnet = hasMagnet;
		this.location = location;
		this.score = score;
		this.trackDifficultyBonus = trackDifficultyBonus;
		this.collectedCurrencyAmount = collectedCurrencyAmount;
		this.collectedDiamondAmount = collectedDiamondAmount;
	}

	public void initialize(Integer trackDifficultyBonus) {
		this.hasMagnet = false;
		this.location = 0;
		this.score = 0;
		this.trackDifficultyBonus = trackDifficultyBonus;
		this.collectedCurrencyAmount = 0;
		this.collectedDiamondAmount = 0;
	}

	/**
	 * @param runScore, Integer value that shows how many points does the player get for running.
	 * @param runningSpeed, Integer value that is the speed of the player.
	 * calls the run function and if the score of the player is higher than 5000, activates magnet.
	 */
	public void update(Integer runScore, Integer runningSpeed) {
		run(runScore, runningSpeed);
		if (score != null && score > 5000 && !hasMagnet) {
			hasMagnet = true;
			System.out.println("Magnet activated");
		}
	}

	public void update() {
		if (score != null && score > 5000 && !hasMagnet) {
			hasMagnet = true;
			System.out.println("Magnet activated");
		}
	}

	
	/**
	 * @param runScore, Integer value that shows how many points does the player get for running.
	 * @param runningSpeed, Integer value that is the speed of the player.
	 * Updates the players location and score by running for "runningSpeed" value and runScore value.
	 */
	public void run(Integer runScore, Integer runningSpeed) {
		location += runningSpeed;
		score += runScore * trackDifficultyBonus;
	}

	/**
	 * @param currentCurrency, currency to be picked
	 * Picks up the currency and updates the corresponding fields.
	 */
	public void pickUpCurrency(Currency<CurrencyType> currentCurrency) {
		if (currentCurrency.getCurrencyType().equals(CurrencyType.DIAMOND)) {
			collectedDiamondAmount++;
		}
		collectedCurrencyAmount++;
		score += currentCurrency.getCurrencyType().getCurrencyValue() * trackDifficultyBonus;
	}

	/**
	 * @param actionScore, score value that the jump actions gives.
	 * @param jumpProbability, probability that is used for calculating the jump possibility
	 * @return true if jump is successful, false otherwise
	 */
	public boolean jump(Integer actionScore, Double jumpProbability) {
		if (new Random().nextDouble() > jumpProbability) {
			score += actionScore * trackDifficultyBonus;
			return true;
		}
		return false;
	}
	
	/**
	 * @param actionScore, score value that the slide actions gives.
	 * @param slideProbability, probability that is used for calculating the slide possibility
	 * @return true if slide is successful, false otherwise
	 */
	public boolean slide(Integer actionScore, Double slideProbability) {
		if (new Random().nextDouble() > slideProbability) {
			score += actionScore * trackDifficultyBonus;
			return true;
		}
		return false;
	}

	public boolean hasMagnet() {
		return hasMagnet;
	}

	public Integer getLocation() {
		return location;
	}

	public Integer getScore() {
		return score;
	}

	public Integer getTrackDifficultyBonus() {
		return trackDifficultyBonus;
	}

	public Integer getCollectedCurrencyAmount() {
		return collectedCurrencyAmount;
	}

	public Integer getCollectedDiamondAmount() {
		return collectedDiamondAmount;
	}
}
