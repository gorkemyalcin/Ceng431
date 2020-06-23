package entities.ships;

import java.util.ArrayList;
import java.util.List;

import entities.PointRange;
import entities.addables.ships.ShipAddable;

public class Destroyer implements Ship {

	private PointRange pointRange;
	private List<ShipAddable> addables;

	public Destroyer() {
		this.pointRange = new PointRange(20, 40);
		this.addables = new ArrayList<>();
	}

	@Override
	public PointRange getPointRange() {
		return pointRange;
	}

	@Override
	public List<ShipAddable> getAddables() {
		return addables;
	}

	@Override
	public void addAddables(ShipAddable addable) {
		addables.add(addable);
	}

	@Override
	public ShipType getShipType() {
		return ShipType.DESTROYER;
	}

	@Override
	public void addAddable(ShipAddable addable) {
		if (!containsAddable(addable.toString())) {
			addables.add(addable);
			System.out.println("Succesfully added the addable.");
		} else {
			System.out.println("This addable already exists in this ship.");
		}
	}

	@Override
	public boolean containsAddable(String shipAddable) {
		for (ShipAddable addable : addables) {
			if (addable.toString().equalsIgnoreCase(shipAddable)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (ShipAddable addable : addables) {
			sb.append(addable);
		}
		return "Cruiser" + pointRange.toString() + ":(" + sb.toString() + ")";
	}

	@Override
	public void printInformation() {
		System.out.println(toString());
	}

	@Override
	public String getName() {
		return "Destroyer";
	}
}
