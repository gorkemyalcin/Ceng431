package decorators;

import entities.addables.planes.MachineGun;
import entities.addables.planes.PlaneAddable;
import entities.planes.Plane;

public class MachineGunPlaneDecorator extends PlaneDecorator {

	public MachineGunPlaneDecorator(Plane plane) {
		super(plane);
		addAddable(new MachineGun());
	}

	@Override
	public void printInformation() {
		plane.printInformation();
	}

	@Override
	public void addAddable(PlaneAddable addable) {
		plane.addAddable(addable);
	}

	@Override
	public String getName() {
		return plane.getName();
	}
}