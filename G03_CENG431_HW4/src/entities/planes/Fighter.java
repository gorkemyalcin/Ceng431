package entities.planes;

import java.util.ArrayList;
import java.util.List;

import entities.PointRange;
import entities.addables.planes.PlaneAddable;
import entities.engines.Engine;

public class Fighter implements Plane {

	private PointRange pointRange;
	private Engine engine;
	private List<PlaneAddable> addables;

	public Fighter() {
		this.pointRange = new PointRange(10, 12);
		this.addables = new ArrayList<>();
	}

	@Override
	public PointRange getPointRange() {
		return pointRange;
	}

	@Override
	public PlaneType getPlaneType() {
		return PlaneType.FIGHTER;
	}

	@Override
	public Engine getEngine() {
		return engine;
	}

	@Override
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
		return "Fighter" + pointRange.toString() + ":" + sb.toString();
	}

	@Override
	public void printInformation() {
		System.out.println(toString());
	}

	@Override
	public String getName() {
		return "Fighter";
	}
}
