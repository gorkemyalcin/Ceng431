package entities.planes;

import java.util.List;

import entities.Entity;
import entities.PointRange;
import entities.addables.planes.PlaneAddable;
import entities.engines.Engine;

public interface Plane extends Entity {

	public PlaneType getPlaneType();

	public void changeEngine(Engine engine);

	public PointRange getPointRange();

	public void addAddable(PlaneAddable addable);

	public boolean containsAddable(String string);

	public Engine getEngine();

	public List<PlaneAddable> getAddables();

	public void printInformation();

}
