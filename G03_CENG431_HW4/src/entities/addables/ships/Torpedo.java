package entities.addables.ships;

import entities.PointRange;

public class Torpedo implements ShipAddable {

	private PointRange pointRange;

	public Torpedo() {
		this.pointRange = new PointRange(3, 5);
	}

	@Override
	public PointRange getPointRange() {
		return pointRange;
	}

	@Override
	public String toString() {
		return "Torpedo(" + pointRange.toString() + ")";
	}

	@Override
	public String getName() {
		return "Torpedo";
	}

	@Override
	public void printInformation() {
		System.out.println(toString());
	}
}
