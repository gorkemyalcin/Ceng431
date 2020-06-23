package entities.ships;

import java.util.List;

import entities.Entity;
import entities.PointRange;
import entities.addables.ships.ShipAddable;

public interface Ship extends Entity {

	public PointRange getPointRange();

	public ShipType getShipType();

	public List<ShipAddable> getAddables();

	public void addAddables(ShipAddable addable);

	public boolean containsAddable(String string);

	void addAddable(ShipAddable addable);

}
