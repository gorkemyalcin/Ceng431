package creators;

import entities.Entity;
import entities.planes.Multirole;

public class MultiroleFactory implements AbstractFactory {

	@Override
	public Entity create() {
		return new Multirole();
	}

}