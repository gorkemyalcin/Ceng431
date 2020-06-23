package entities.planes;

import java.util.ArrayList;
import java.util.List;

import entities.PointRange;
import entities.addables.planes.PlaneAddable;
import entities.engines.Engine;

public class Bomber implements Plane {

	private PointRange pointRange;
	private Engine engine;
	private List<PlaneAddable> addables;
	private PlaneType planeType;

	public Bomber() {
		this.pointRange = new PointRange(15, 20);
		this.addables = new ArrayList<>();
		this.planeType = PlaneType.BOMBER;
	}

	@Override
	public PointRange getPointRange() {
		return pointRange;
	}

	@Override
	public PlaneType getPlaneType() {
		return planeType;
	}

	@Override
	public Engine getEngine() {
		return engine;
	}

	public List<PlaneAddable> getAddables() {
		return addables;
	}

	@Override
	public void changeEngine(Engine engine) {
		this.engine = engine;
		System.out.println("Succesfully changed the engine");
	}

	@Override
	public void addAddable(PlaneAddable addable) {
		if (!containsAddable(addable.toString())) {
			addables.add(addable);
			System.out.println("Succesfully added the addable.");
		} else {
			System.out.println("This addable already exists in this plane.");
		}
	}

	@Override
	public boolean containsAddable(String planeAddable) {
		for (PlaneAddable addable : addables) {
			if (addable.toString().equalsIgnoreCase(planeAddable)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		if (engine != null) {
			sb.append("[" + engine + "]");
		}
		for (PlaneAddable addable : addables) {
			sb.append("(" + addable + ") ");
		}
		return "Bomber" + pointRange.toString() + ":" + sb.toString();
	}

	@Override
	public void printInformation() {
		System.out.println(toString());
	}

	public void setPlaneType(PlaneType planeType) {
		this.planeType = planeType;
	}

	public void setPointRange(PointRange pointRange) {
		this.pointRange = pointRange;
	}

	@Override
	public String getName() {
		return "Bomber";
	}

}
