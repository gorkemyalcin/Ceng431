package simulation;

import java.util.ArrayList;
import java.util.List;

import entities.Entity;
import entities.addables.planes.PlaneAddable;
import entities.addables.ships.ShipAddable;
import entities.engines.Engine;
import entities.planes.Plane;
import entities.ships.Ship;

public class Player {

	private String name;
	private List<Ship> ownedShips;
	private List<Plane> ownedPlanes;

	public Player(String name) {
		this.name = name;
		this.ownedShips = new ArrayList<>();
		this.ownedPlanes = new ArrayList<>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Ship> getOwnedShips() {
		return ownedShips;
	}

	public void setOwnedShips(List<Ship> ownedShips) {
		this.ownedShips = ownedShips;
	}

	public List<Plane> getOwnedPlanes() {
		return ownedPlanes;
	}

	public void setOwnedPlanes(List<Plane> ownedPlanes) {
		this.ownedPlanes = ownedPlanes;
	}

	public void addPlane(Entity plane) {
		if (ownedPlanes.size() < 3) {
			ownedPlanes.add((Plane) plane);
			System.out.println("Sucesfully added the plane");
		} else {
			System.out.println("Player can not own more than 3 planes.");
		}
	}

	public void addShip(Entity ship) {
		if (ownedShips.size() < 3) {
			ownedShips.add((Ship) ship);
			System.out.println("Sucesfully added the ship");
		} else {
			System.out.println("Player can not own more than 3 ships.");
		}
	}

	public boolean hasVehicles() {
		return ownedShips.size() + ownedPlanes.size() != 0;
	}

	public void printVehicles() {
		Integer count = 1;
		for (Plane plane : ownedPlanes) {
			System.out.println(count + ": " + plane);
			count++;
		}
		for (Ship ship : ownedShips) {
			System.out.println(count + ": " + ship);
			count++;
		}
	}

	public Entity getVehicle(Integer vehicleChoice) {
		if (vehicleChoice < ownedPlanes.size()) {
			return ownedPlanes.get(vehicleChoice);
		} else if (vehicleChoice - ownedPlanes.size() < ownedShips.size()) {
			return ownedShips.get(vehicleChoice - ownedPlanes.size());
		}
		return null;
	}

	public boolean hasPlanes() {
		return !ownedPlanes.isEmpty();
	}

	public void printPlanes() {
		Integer count = 1;
		for (Plane plane : ownedPlanes) {
			System.out.println(count + ": " + plane);
			count++;
		}
	}

	public Plane getPlane(int planeIndex) {
		if (planeIndex < ownedPlanes.size()) {
			return ownedPlanes.get(planeIndex);
		}
		return null;
	}

	public void reset() {
		ownedPlanes.clear();
		ownedShips.clear();
	}

	public Integer getPlanePoints() {
		Integer totalPoints = 0;
		for (Plane plane : ownedPlanes) {
			Integer planePoint = plane.getPointRange().getRandomPointBetweenPoints();
			totalPoints += planePoint;
			printPoints(plane, planePoint);
			Engine engine = plane.getEngine();
			if (engine != null) {
				Integer enginePoint = engine.getPointRange().getRandomPointBetweenPoints();
				totalPoints += enginePoint;
				printPoints(engine, enginePoint);
			}
			for (PlaneAddable addable : plane.getAddables()) {
				Integer addablePoint = addable.getPointRange().getRandomPointBetweenPoints();
				totalPoints += addablePoint;
				printPoints(addable, addablePoint);
			}
		}
		return totalPoints;
	}

	public Integer getShipPoints() {
		Integer totalPoints = 0;
		for (Ship ship : ownedShips) {
			Integer shipPoint = ship.getPointRange().getRandomPointBetweenPoints();
			totalPoints += shipPoint;
			printPoints(ship, shipPoint);
			for (ShipAddable addable : ship.getAddables()) {
				Integer addablePoint = addable.getPointRange().getRandomPointBetweenPoints();
				totalPoints += addablePoint;
				printPoints(addable, addablePoint);
			}
		}
		return totalPoints;
	}

	private void printPoints(Entity entity, Integer point) {
		System.out.println(entity.getName() + "[" + point + "]");
	}

	public Integer getPoints() {
		return getPlanePoints() + getShipPoints();
	}
}
