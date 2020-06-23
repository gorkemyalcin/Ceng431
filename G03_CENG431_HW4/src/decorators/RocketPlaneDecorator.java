package decorators;

import entities.addables.Rocket;
import entities.addables.planes.PlaneAddable;
import entities.planes.Plane;

public class RocketPlaneDecorator extends PlaneDecorator {

	public RocketPlaneDecorator(Plane plane) {
		super(plane);
		addAddable(new Rocket());
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