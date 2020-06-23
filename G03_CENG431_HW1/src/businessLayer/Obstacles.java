package businessLayer;

import java.util.ArrayList;
import java.util.List;

import businessLayer.enums.ObstacleType;

/**
 * A class used for storing obstacles.
 *
 */
public class Obstacles {

	private List<Obstacle<ObstacleType>> obstacleList;

	public Obstacles() {

	}

	public Obstacles(List<Obstacle<ObstacleType>> obstacleList) {
		this.obstacleList = obstacleList;
	}

	/**
	 * @param trackLength,             an integer value that is used for creating
	 *                                 the obstacles on the track.
	 * @param obstacleLengthFrequency, an integer value that is used to calculate
	 *                                 how many obstacles should be created for the
	 *                                 track. Initializes the obstacles on the
	 *                                 track.
	 */
	public void initialize(Integer trackLength, Integer obstacleLengthFrequency) {
		this.obstacleList = new ArrayList<>();
		for (int i = 0; i < trackLength / obstacleLengthFrequency; i++) {
			Obstacle<ObstacleType> obstacle = new Obstacle<ObstacleType>();
			obstacle.initialize((i + 1) * obstacleLengthFrequency);
			obstacleList.add(obstacle);
		}
	}

	/**
	 * Returns the first (smallest distance from the start point) currency
	 */
	public Obstacle<ObstacleType> getCurrentObstacle() {
		if (obstacleList != null && !obstacleList.isEmpty()) {
			return obstacleList.get(0);
		}
		return null;
	}

	/**
	 * Removes the first (smallest distance from the start point) currency
	 */
	public void removeFirstObstacle() {
		if (obstacleList != null && !obstacleList.isEmpty()) {
			obstacleList.remove(0);
		}
	}

	public void addAdditionalObstacles(Integer currentLocation, Integer obstacleLengthFrequency) {
		for (int i = 0; i < currentLocation / obstacleLengthFrequency; i++) {
			Obstacle<ObstacleType> obstacle = new Obstacle<>();
			obstacle.initialize(((i + 1) * obstacleLengthFrequency) + currentLocation);
			obstacleList.add(obstacle);
		}
	}
}
