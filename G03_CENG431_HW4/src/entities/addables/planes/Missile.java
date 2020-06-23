package entities.addables.planes;

import entities.PointRange;

public class Missile implements PlaneAddable {

	private PointRange pointRange;

	public Missile() {
		this.pointRange = new PointRange(3, 6);
	}

	@Override
	public PointRange getPointRange() {
		return pointRange;
	}

	@Override
	public String toString() {
		return "Missile(" + pointRange.toString() + ")";
	}

	@Override
	public String getName() {
		return "Missile";
	}

	@Override
	public void printInformation() {
		System.out.println(toString());
	}
}
