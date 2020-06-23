package decorators;

import entities.addables.planes.Bomb;
import entities.addables.planes.PlaneAddable;
import entities.planes.Plane;

public class BombPlaneDecorator extends PlaneDecorator {

	
	public BombPlaneDecorator(Plane plane) {
		super(plane);
		addAddable(new Bomb());
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
