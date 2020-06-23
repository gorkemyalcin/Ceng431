package decorators;

import entities.addables.planes.Missile;
import entities.addables.planes.PlaneAddable;
import entities.planes.Plane;

public class MissilePlaneDecorator extends PlaneDecorator {

	public MissilePlaneDecorator(Plane plane) {
		super(plane);
		addAddable(new Missile());
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
