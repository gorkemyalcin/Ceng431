package entities.addables.planes;

import entities.Entity;
import entities.PointRange;

public interface PlaneAddable extends Entity {

	public PointRange getPointRange();

	public String getName();

}
