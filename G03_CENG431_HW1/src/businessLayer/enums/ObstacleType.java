package businessLayer.enums;

public enum ObstacleType {
	FALLEN_TREE(2), ROCK(2), SAW(2), AQUADECT(2);

	private Integer obstacleScore;

	ObstacleType(Integer obstacleScore) {
		this.obstacleScore = obstacleScore;
	}

	public Integer getObstacleScore() {
		return obstacleScore;
	}
}
