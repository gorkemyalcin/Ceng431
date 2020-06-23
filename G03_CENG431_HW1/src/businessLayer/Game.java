package businessLayer;

import java.awt.event.WindowEvent;

import businessLayer.enums.CurrencyType;
import businessLayer.enums.ObstacleType;
import exceptions.IncorrectButtonException;

/**
 * The mediator class that holds all the information related to game. The
 * initialized static final fields are the "settings" of the game. Changing
 * these values from here will change the whole game. By changing these fields,
 * we do not need to change other fields in anywhere since almost all of the
 * objects use these fields as parameters.
 * 
 */
public class Game {

	private static final Integer CURRENCY_LENGTH_FREQUENCY = 50;
	private static final Integer OBSTACLE_LENGTH_FREQUENCY = 500;
	private static final Double PLAYER_JUMP_FAIL_PROBABILITY = 0.05;
	private static final Double PLAYER_SLIDE_FAIL_PROBABILITY = 0.05;
	private static final Integer MONSTER_ATTACK_LENGTH_FREQUENCY = 1500;
	private static final Double MONSTER_ATTACK_PROBABILITY = 0.02;
	private static final Integer PLAYER_ACTION_SCORE = 2;
	private static final Integer PLAYER_RUN_SCORE = 1;
	private static final Integer PLAYER_RUNNING_SPEED = 1;
	private static final Integer MIN_TRACK_LENGTH = 1000;
	private static final Integer MAX_TRACK_LENGTH = 10000;

	private Player player;
	private Track track;
	private Currencies currencies;
	private Obstacles obstacles;
	private Monster monster;
	private String whyTheGameEnded;
	private DummyFrame dummyFrameForNonBlockingUserInput;

	public Game(Player player, Track track, Currencies currencies, Obstacles obstacles, Monster monster,
			String whyTheGameEnded, DummyFrame dummyFrameForNonBlockingUserInput) {
		super();
		this.player = player;
		this.track = track;
		this.currencies = currencies;
		this.obstacles = obstacles;
		this.monster = monster;
		this.whyTheGameEnded = whyTheGameEnded;
		this.dummyFrameForNonBlockingUserInput = dummyFrameForNonBlockingUserInput;
	}

	public Game() {
		this.track = new Track();
		this.monster = new Monster();
		this.player = new Player();
		this.currencies = new Currencies();
		this.obstacles = new Obstacles();
		this.whyTheGameEnded = "";
		this.dummyFrameForNonBlockingUserInput = new DummyFrame();
	}

	/**
	 * The method that starts the game. It creates the game, while the game is
	 * active, updates the game and finally when the game is over it finalizes the
	 * game.
	 */
	public void start() {
		initialize();
		while (isGameActive()) {
			update();
		}
		conclude();
	}

	/**
	 * @return true if game is active, false otherwise. If the user pressed Q or the
	 *         game ended naturally, game is finished. For the other button presses
	 *         we threw an exception and caught it.
	 */
	private boolean isGameActive() {
		try {
			checkForOtherButtonPresses();
		} catch (IncorrectButtonException e) {
			System.out.println(e.getMessage());
		}
		return (whyTheGameEnded == null || whyTheGameEnded.equals("")) && !checkForQButtonPress();
	}

	private boolean checkForQButtonPress() {
		if (dummyFrameForNonBlockingUserInput.isQPressed()) {
			whyTheGameEnded = "Q is pressed.";
			System.out.println("Q is pressed, game is over.");
			return true;
		}
		return false;
	}

	/**
	 * @throws IncorrectButtonException If any other button is pressed, we throw an
	 *                                  IncorrectButtonException.
	 */
	private void checkForOtherButtonPresses() throws IncorrectButtonException {
		if (dummyFrameForNonBlockingUserInput.isAnyOtherButtonThanQPressed()) {
			throw new IncorrectButtonException("Press Q to exit, pressing any other button will do nothing.");
		}
	}

	/**
	 * Initializes the track, player, currencies and the obstacles for the game by
	 * using the private static final fields in this class.
	 */
	private void initialize() {
		track.initialize(MIN_TRACK_LENGTH, MAX_TRACK_LENGTH);
		player.initialize(track.getTrackDifficulty().getTrackDifficultyBonus());
		currencies.initialize(track.getTrackLength(), CURRENCY_LENGTH_FREQUENCY);
		obstacles.initialize(track.getTrackLength(), OBSTACLE_LENGTH_FREQUENCY);
		System.out.println("Track difficulty: " + track.getTrackDifficulty() + "(x"
				+ track.getTrackDifficulty().getTrackDifficultyBonus() + ")");
		System.out.println("Track length: " + track.getTrackLength() + " meters");
	}

	/**
	 * Game calls this method in a while loop, updates the game in every iteration.
	 */
	private void update() {
		player.update(PLAYER_RUN_SCORE, PLAYER_RUNNING_SPEED);
		checkForQButtonPress();
		checkForCurrency();
		checkForObstacles();
		checkForMonsterAttack();
		updateCurrencies();
		updateObstacles();
	}

	private void updateObstacles() {
		if (isThereAreNoMoreObstaclesLeft()) {
			obstacles.addAdditionalObstacles(player.getLocation(), OBSTACLE_LENGTH_FREQUENCY);
		}
	}

	private boolean isThereAreNoMoreObstaclesLeft() {
		return obstacles.getCurrentObstacle() == null;
	}

	private void updateCurrencies() {
		if (isThereAreNoMoreCurrenciesLeft()) {
			currencies.addAdditionalCurrencies(player.getLocation(), CURRENCY_LENGTH_FREQUENCY);
		}
	}

	private boolean isThereAreNoMoreCurrenciesLeft() {
		return currencies.getCurrentCurrency() == null;
	}

	/**
	 * First it checks if the obstacle is near the player, if it is, it gets the
	 * currenctObstacle and according to the obstacleType, it either calls the slide
	 * or jump method of the player. It removes the obstacle from the obstacle list.
	 */
	private void checkForObstacles() {
		if (isObstacleNearby(player.getLocation())) {
			Obstacle<ObstacleType> currentObstacle = obstacles.getCurrentObstacle();
			if (currentObstacle.getObstacleType().equals(ObstacleType.FALLEN_TREE)
					|| currentObstacle.getObstacleType().equals(ObstacleType.AQUADECT)) {
				if (player.slide(PLAYER_ACTION_SCORE, PLAYER_SLIDE_FAIL_PROBABILITY)) {
					System.out.println("Player location: " + player.getLocation() + ", Player score: "
							+ player.getScore() + ", Slide successful");
				} else {
					whyTheGameEnded = "Slide failed, game over.";
					System.out.println("Player location: " + player.getLocation() + ", Player score: "
							+ player.getScore() + ", Slide failed, game over.");
				}
			} else {
				if (player.jump(PLAYER_ACTION_SCORE, PLAYER_JUMP_FAIL_PROBABILITY)) {
					System.out.println("Player location: " + player.getLocation() + ", Player score: "
							+ player.getScore() + ", Jump successful");
				} else {
					whyTheGameEnded = "Jump failed, game over.";
					System.out.println("Player location: " + player.getLocation() + ", Player score: "
							+ player.getScore() + ", Jump failed, game over.");
				}
			}
			obstacles.removeFirstObstacle();
		}
	}

	/**
	 * If it is time to attack the player (for every x meters) it invokes the
	 * monster's attack method. Depending on the result it either ends the game or
	 * it keeps the game running.
	 */
	private void checkForMonsterAttack() {
		if (monster.checkIfItIsTimeToAttack(player.getLocation(), MONSTER_ATTACK_LENGTH_FREQUENCY) && isGameActive()) {
			if (monster.attack(MONSTER_ATTACK_PROBABILITY)) {
				whyTheGameEnded = "Monster ate the player";
				System.out.println("Player location: " + player.getLocation() + ", Player score: " + player.getScore()
						+ ", Monster ate the player, game over.");
			} else {
				System.out.println("Player location: " + player.getLocation() + ", Player score: " + player.getScore()
						+ " Player dodged the monster's attack.");
			}
		}
	}

	/**
	 * First it checks if the currency is near the player, if it is, it gets the
	 * currenctCurrency and according to the currencyType, depending on the player's
	 * magnet situation it either picks the currency or doesn't. It removes the
	 * currency from the currency list.
	 */
	private void checkForCurrency() {
		if (isCurrencyNearby(player.getLocation())) {
			Currency<CurrencyType> currentCurrency = currencies.getCurrentCurrency();
			if (currentCurrency.getCurrencyType().equals(CurrencyType.MAGNETIC_COIN)) {
				if (player.hasMagnet()) {
					player.pickUpCurrency(currentCurrency);
					System.out.println("Player location: " + player.getLocation() + ", Player score: "
							+ player.getScore() + ", Picked up a magnetic coin");
				} else {
					System.out
							.println("Player location: " + player.getLocation() + ", Player score: " + player.getScore()
									+ ", Player does not have a magnet, couldn't pick up the magnetic coin");
				}
			} else {
				player.pickUpCurrency(currentCurrency);
				System.out.println("Player location: " + player.getLocation() + ", Player score: " + player.getScore()
						+ ", Picked up a " + currentCurrency.getCurrencyType().toString().toLowerCase());
			}
			currencies.removeFirstCurrency();
			player.update();// Just for looking if the player has enough points for magnet upgrade.
		}
	}

	/**
	 * At the end of the game, creates a FinishReport object with the wanted fields
	 * and calls the FinishReportPrinter objects conclude method to print the JSON
	 * object and close the dummy frame.
	 */
	private void conclude() {
		FinishReport finishReport = new FinishReport(track.getTrackDifficulty(), track.getTrackType(),
				track.getTrackLength(), whyTheGameEnded, player.getCollectedCurrencyAmount(),
				player.getCollectedDiamondAmount(), player.getScore());
		FinishReportUtil.conclude(finishReport);
		dummyFrameForNonBlockingUserInput
				.dispatchEvent(new WindowEvent(dummyFrameForNonBlockingUserInput, WindowEvent.WINDOW_CLOSING));
	}

	/**
	 * @param playerLocation, Player's current location
	 * @return true if an obstacle is near the player, false otherwise.
	 */
	private boolean isObstacleNearby(Integer playerLocation) {
		Event<ObstacleType> firstObstacle = obstacles.getCurrentObstacle();
		if (firstObstacle != null) {
			if (playerLocation.equals(firstObstacle.getEventLocation())) {
				return true;
			}
		}
		return false;
	}

	/**
	 * @param playerLocation, Player's current location
	 * @return true if an currency is near the player, false otherwise.
	 */
	private boolean isCurrencyNearby(Integer playerLocation) {
		Event<?> firstCurrency = currencies.getCurrentCurrency();
		if (firstCurrency != null) {
			if (playerLocation.equals(firstCurrency.getEventLocation())) {
				return true;
			}
		}
		return false;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public Track getTrack() {
		return track;
	}

	public void setTrack(Track track) {
		this.track = track;
	}

	public Currencies getCurrencies() {
		return currencies;
	}

	public void setCurrencies(Currencies currencies) {
		this.currencies = currencies;
	}

	public Obstacles getObstacles() {
		return obstacles;
	}

	public void setObstacles(Obstacles obstacles) {
		this.obstacles = obstacles;
	}

	public Monster getMonster() {
		return monster;
	}

	public void setMonster(Monster monster) {
		this.monster = monster;
	}

	public String getWhyTheGameEnded() {
		return whyTheGameEnded;
	}

	public void setWhyTheGameEnded(String whyTheGameEnded) {
		this.whyTheGameEnded = whyTheGameEnded;
	}

}
