package decorators;

import java.util.List;

import entities.PointRange;
import entities.addables.ships.ShipAddable;
import entities.ships.Ship;
import entities.ships.ShipType;

public abstract class ShipDecorator implements Ship{

	protected Ship ship;

	public ShipDecorator(Ship ship) {
		this.ship = ship;
	}

	public void addAddable(ShipAddable addable) {
		ship.addAddable(addable);
	}

	public ShipType getShipType() {
		return ship.getShipType();
	}

	public PointRange getPointRange() {
		return ship.getPointRange();
	}

	public List<ShipAddable> getAddables() {
		return ship.getAddables();
	}

	public boolean containsAddable(String shipAddable) {
		return ship.containsAddable(shipAddable);
	}

	public void printInformation() {
		ship.printInformation();
	}

	@Override
	public void addAddables(ShipAddable addable) {
		ship.addAddable(addable);
	}
	
}
