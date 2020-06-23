package decorators;

import entities.addables.ships.Cannon;
import entities.addables.ships.ShipAddable;
import entities.ships.Ship;

public class CannonShipDecorator extends ShipDecorator {

	public CannonShipDecorator(Ship ship) {
		super(ship);
		addAddable(new Cannon());
	}

	@Override
	public void printInformation() {
		ship.printInformation();
	}

	@Override
	public void addAddable(ShipAddable addable) {
		ship.addAddable(addable);
	}

	@Override
	public String getName() {
		return ship.getName();
	}
}