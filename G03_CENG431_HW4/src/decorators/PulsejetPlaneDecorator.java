package decorators;

import entities.engines.Engine;
import entities.engines.Pulsejet;
import entities.planes.Plane;

public class PulsejetPlaneDecorator extends PlaneDecorator {


	public PulsejetPlaneDecorator(Plane plane) {
		super(plane);
		changeEngine(new Pulsejet());
	}

	public String toString() {
		return plane.toString();
	}

	@Override
	public void printInformation() {
		plane.printInformation();
	}

	@Override
	public void changeEngine(Engine engine) {
		plane.changeEngine(engine);
	}

	@Override
	public String getName() {
		return plane.getName();
	}
}
