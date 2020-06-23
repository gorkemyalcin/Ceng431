package entities.engines;

import entities.PointRange;

public class Turbojet implements Engine {

	private PointRange pointRange;

	public Turbojet() {
		this.pointRange = new PointRange(5, 7);
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
		return "Turbojet";
	}
}
