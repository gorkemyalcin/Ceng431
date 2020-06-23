package entities.addables.planes;

import entities.PointRange;

public class Bomb implements PlaneAddable {
	private PointRange pointRange;

	public Bomb() {
		this.pointRange = new PointRange(0, 10);
	}

	@Override
	public PointRange getPointRange() {
		return pointRange;
	}

	@Override
	public String toString() {
		return "Bomb(" + pointRange.toString() + ")";
	}

	@Override
	public String getName() {
		return "Bomb";
	}

	@Override
	public void printInformation() {
		System.out.println(toString());
	}
}
