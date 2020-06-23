package creators;

import entities.Entity;
import entities.ships.Frigate;

public class FrigateFactory implements AbstractFactory {

	@Override
	public Entity create() {
		return new Frigate();
	}

}
