package decorators;

import entities.engines.Engine;
import entities.engines.Turbojet;
import entities.planes.Plane;

public class TurbojetPlaneDecorator extends PlaneDecorator {

	public TurbojetPlaneDecorator(Plane plane) {
		super(plane);
		changeEngine(new Turbojet());
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
