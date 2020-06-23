package decorators;

import entities.addables.ships.ShipAddable;
import entities.addables.Rocket;
import entities.ships.Ship;

public class RocketShipDecorator extends ShipDecorator {

	public RocketShipDecorator(Ship ship) {
		super(ship);
		addAddable(new Rocket());
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