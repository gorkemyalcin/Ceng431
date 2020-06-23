package decorators;

import java.util.List;

import entities.PointRange;
import entities.addables.planes.PlaneAddable;
import entities.engines.Engine;
import entities.planes.Plane;
import entities.planes.PlaneType;

public abstract class PlaneDecorator implements Plane {

	protected Plane plane;

	public PlaneDecorator(Plane plane) {
		this.plane = plane;
	}

	public void addAddable(PlaneAddable addable) {
		plane.addAddable(addable);
	}

	public void changeEngine(Engine engine) {
		plane.changeEngine(engine);
	}

	public PlaneType getPlaneType() {
		return plane.getPlaneType();
	}

	public PointRange getPointRange() {
		return plane.getPointRange();
	}
	
	public List<PlaneAddable> getAddables(){
		return plane.getAddables();
	}

	public boolean containsAddable(String planeAddable) {
		return plane.containsAddable(planeAddable);
	}

	public Engine getEngine() {
		return plane.getEngine();
	}
	
	public void printInformation() {
		plane.printInformation();
	}
}
