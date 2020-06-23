package entities.addables.planes;

import entities.PointRange;

public class MachineGun implements PlaneAddable {

	private PointRange pointRange;

	public MachineGun() {
		this.pointRange = new PointRange(1, 3);
	}

	@Override
	public PointRange getPointRange() {
		return pointRange;
	}

	@Override
	public String toString() {
		return "Machine Gun(" + pointRange.toString() + ")";
	}

	@Override
	public String getName() {
		return "Machine Gun";
	}

	@Override
	public void printInformation() {
		System.out.println(toString());
	}
}
