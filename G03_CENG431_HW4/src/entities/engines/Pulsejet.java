package entities.engines;

import entities.PointRange;

public class Pulsejet implements Engine {

	private PointRange pointRange;

	public Pulsejet() {
		this.pointRange = new PointRange(2, 4);
	}

	public String toString() {
		return "Pulsejet: " + pointRange.getFirstPoint() + "," + pointRange.getSecondPoint();
	}

	@Override
	public PointRange getPointRange() {
		return pointRange;
	}

	@Override
	public void printInformation() {
		System.out.println(toString());
	}

	@Override
	public String getName() {
		return "Pulsejet";
	}
}
