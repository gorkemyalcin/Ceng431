package creators;

import entities.Entity;
import entities.planes.Bomber;

public class BomberFactory implements AbstractFactory {

	@Override
	public Entity create() {
		return new Bomber();
	}

}