package creators;

import entities.Entity;
import entities.ships.Cruiser;

public class CruiserFactory implements AbstractFactory {

	@Override
	public Entity create() {
		return new Cruiser();
	}

}
