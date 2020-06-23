package creators;

import entities.Entity;
import entities.planes.Fighter;

public class FighterFactory implements AbstractFactory {

	@Override
	public Entity create() {
		return new Fighter();
	}

}