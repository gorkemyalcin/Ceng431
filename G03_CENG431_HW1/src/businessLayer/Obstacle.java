package businessLayer;

import java.util.Random;

import businessLayer.enums.ObstacleType;

public class Obstacle<T> extends Event<T> {

	private ObstacleType obstacleType;

	public Obstacle() {
		super();
	}

	public Obstacle(T type, Integer obstacleLocation) {
		super(obstacleLocation, type);
	}
	
	/**
	 * @param location, an Integer value that is used for creating the obstacle at that location.
	 * Initializes the current obstacle by the location of the currency and the type of the currency.
	 */
	public void initialize(Integer location) {
		super.setEventLocation(location);

		ObstacleType type;
		Random random = new Random();
		Double obstacleTypeProbability = random.nextDouble();
		if (obstacleTypeProbability < 0.25) {
			type = ObstacleType.FALLEN_TREE;
		} else if (obstacleTypeProbability < 0.5) {
			type = ObstacleType.ROCK;
		} else if (obstacleTypeProbability < 0.75) {
			type = ObstacleType.AQUADECT;
		} else {
			type = ObstacleType.SAW;
		}
		this.obstacleType = type;

	}

	public ObstacleType getObstacleType() {
		return obstacleType;
	}
}
