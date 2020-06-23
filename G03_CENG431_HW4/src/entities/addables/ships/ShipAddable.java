package entities.addables.ships;

import entities.Entity;
import entities.PointRange;

public interface ShipAddable extends Entity{
	
	public PointRange getPointRange();
	
	public String getName();

}
