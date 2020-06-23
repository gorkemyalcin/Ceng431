package creators;

import entities.Entity;
import entities.ships.Destroyer;

public class DestroyerFactory implements AbstractFactory {

	@Override
	public Entity create() {
		return new Destroyer();
	}

}