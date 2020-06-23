package entities;

import java.util.concurrent.ThreadLocalRandom;

public class PointRange {

	private Integer firstPoint;
	private Integer secondPoint;

	public PointRange(Integer firstPoint, Integer secondPoint) {
		super();
		this.firstPoint = firstPoint;
		this.secondPoint = secondPoint;
	}

	public String toString() {
		return "[" + firstPoint + "," + secondPoint + "]";
	}

	public Integer getRandomPointBetweenPoints() {
		return ThreadLocalRandom.current().nextInt(firstPoint, secondPoint + 1);
	}

	public Integer getFirstPoint() {
		return firstPoint;
	}

	public void setFirstPoint(Integer firstPoint) {
		this.firstPoint = firstPoint;
	}

	public Integer getSecondPoint() {
		return secondPoint;
	}

	public void setSecondPoint(Integer secondPoint) {
		this.secondPoint = secondPoint;
	}

}
