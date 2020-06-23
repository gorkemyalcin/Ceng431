package entities.addables;

import entities.PointRange;
import entities.addables.planes.PlaneAddable;
import entities.addables.ships.ShipAddable;

public class Rocket implements ShipAddable, PlaneAddable {

	private PointRange pointRange;

	public Rocket() {
		this.pointRange = new PointRange(2, 8);
	}

	@Override
	public PointRange getPointRange() {
		return pointRange;
	}

	@Override
	public String toString() {
		return "Ship Rocket(" + pointRange.toString() + ")";
	}

	@Override
	public String getName() {
		return "Ship Rocket";
	}

	@Override
	public void printInformation() {
		System.out.println(toString());
	}
}
