package entities.addables.ships;

import entities.PointRange;

public class Cannon implements ShipAddable {

	private PointRange pointRange;

	public Cannon() {
		this.pointRange = new PointRange(3, 7);
	}

	@Override
	public PointRange getPointRange() {
		return pointRange;
	}

	@Override
	public String toString() {
		return "Cannon(" + pointRange.toString() + ")";
	}

	@Override
	public String getName() {
		return "Cannon";
	}

	@Override
	public void printInformation() {
		System.out.println(toString());
	}
}
