package decorators;

import entities.addables.ships.ShipAddable;
import entities.addables.ships.Torpedo;
import entities.ships.Ship;

public class TorpedoShipDecorator extends ShipDecorator {

	public TorpedoShipDecorator(Ship ship) {
		super(ship);
		addAddable(new Torpedo());
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
