package simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

import creators.AbstractFactory;
import creators.BomberFactory;
import creators.CruiserFactory;
import creators.DestroyerFactory;
import creators.FighterFactory;
import creators.FrigateFactory;
import creators.MultiroleFactory;
import decorators.BombPlaneDecorator;
import decorators.CannonShipDecorator;
import decorators.MachineGunPlaneDecorator;
import decorators.MissilePlaneDecorator;
import decorators.PulsejetPlaneDecorator;
import decorators.RocketPlaneDecorator;
import decorators.RocketShipDecorator;
import decorators.TorpedoShipDecorator;
import decorators.TurbojetPlaneDecorator;
import entities.Entity;
import entities.addables.planes.PlaneAddable;
import entities.addables.ships.ShipAddable;
import entities.planes.Plane;
import entities.ships.Ship;

public class Simulation {

	private Player firstPlayer;
	private Player secondPlayer;
	private AbstractFactory bomberFactory;
	private AbstractFactory cruiserFactory;
	private AbstractFactory destroyerFactory;
	private AbstractFactory fighterFactory;
	private AbstractFactory frigateFactory;
	private AbstractFactory multiroleFactory;
	private static final List<String> PLANE_ENGINES = Arrays.asList("Pulsejet", "Turbojet");
	private static final List<String> PLANE_ADDABLES = Arrays.asList("Bomb", "Machine Gun", "Missile", "Plane Rocket");
	private static final List<String> SHIP_ADDABLES = Arrays.asList("Cannon", "Ship Rocket", "Torpedo");

	public Simulation() {
		this.firstPlayer = new Player("First player");
		this.secondPlayer = new Player("Second player");
		this.bomberFactory = new BomberFactory();
		this.cruiserFactory = new CruiserFactory();
		this.destroyerFactory = new DestroyerFactory();
		this.fighterFactory = new FighterFactory();
		this.frigateFactory = new FrigateFactory();
		this.multiroleFactory = new MultiroleFactory();

	}

	public void run() {
		while (true) {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			printMainMenu();
			try {
				String choice = br.readLine();
				if (choice.equals("1")) {
					printPlayerOperations();
					String playerOperationChoice = br.readLine();
					if (playerOperationChoice.equals("1")) {
						addBattleItemsToPlane(br);
					} else if (playerOperationChoice.equals("2")) {
						addAddablesToExistingVehicles(br);
					} else if (playerOperationChoice.equals("3")) {
						changeTheEngineOfAnExistingPlane(br);
					} else if (playerOperationChoice.equals("4")) {
						printAllItems();
					}
				} else if (choice.equals("2")) {
					simulate();
				} else if (choice.equals("3")) {
					reset();
				} else if (choice.equalsIgnoreCase("x")) {
					break;
				} else {
					printWrongInputMessage();
				}
			} catch (IOException e) {
				System.out.println("Error occured with I/O operations");
			}
		}

	}

	private void changeTheEngineOfAnExistingPlane(BufferedReader br) throws IOException {
		Player player = choosePlayerWithInput(br);
		if (player != null) {
			if (player.hasPlanes()) {
				player.printPlanes();
				String planeIndex = br.readLine();
				Plane plane = player.getPlane(Integer.parseInt(planeIndex) - 1);
				if (plane != null) {
					changeEngineOfThePlane(plane, br);
				} else {
					System.out.println("Choose a plane from the list.");
				}
			} else {
				System.out.println("This player does not have any planes.");
			}
		}
	}

	private void addAddablesToExistingVehicles(BufferedReader br) throws IOException {
		Player player = choosePlayerWithInput(br);
		if (player != null) {
			if (player.hasVehicles()) {
				player.printVehicles();
				System.out.println("Choose a vehicle:");
				Integer vehicleChoice = Integer.parseInt(br.readLine());
				if (vehicleChoice > 0 && vehicleChoice < 7) {
					Entity vehicle = player.getVehicle(vehicleChoice - 1);
					if (vehicle != null) {
						if (vehicle instanceof Plane) {
							Plane plane = (Plane) vehicle;
							addAddableToPlane(plane, br);
						} else if (vehicle instanceof Ship) {
							Ship ship = (Ship) vehicle;
							addAddableToShip(ship, br);
						}
					} else {
						System.out.println("Please choose a vehicle from the list.");
					}
				}
			} else {
				printNoVehicles();
			}
		}
	}

	private void addBattleItemsToPlane(BufferedReader br) throws IOException {
		Player player = choosePlayerWithInput(br);
		if (player != null) {
			printAddingOperations();
			String addChoice = br.readLine();
			if (addChoice.equals("1")) {
				addPlaneToPlayer(br, player);
			} else if (addChoice.equals("2")) {
				addShipsToPlayer(br, player);
			} else {
				System.out.println("Please choose either 1 or 2.");
			}
		}
	}

	private void changeEngineOfThePlane(Plane plane, BufferedReader br) throws IOException {
		printEngineModificationChoices(plane);
		String engineChoice = br.readLine();
		if (engineChoice.equalsIgnoreCase("1") && (plane.getEngine() == null
				|| !plane.getEngine().toString().equalsIgnoreCase(PLANE_ENGINES.get(0)))) {
			plane = new PulsejetPlaneDecorator(plane);
		} else if (engineChoice.equalsIgnoreCase("2") && (plane.getEngine() == null
				|| !plane.getEngine().toString().equalsIgnoreCase(PLANE_ENGINES.get(1)))) {
			plane = new TurbojetPlaneDecorator(plane);
		} else {
			System.out.println("The engine you specified does not exist.");
		}
	}

	private void addAddableToShip(Ship ship, BufferedReader br) throws IOException {
		printShipAddablesChoices(ship);
		String addableChoice = br.readLine();
		if (addableChoice.equalsIgnoreCase("1") && !ship.containsAddable(SHIP_ADDABLES.get(0))) {
			ship = new CannonShipDecorator(ship);
		} else if (addableChoice.equalsIgnoreCase("2") && !ship.containsAddable(SHIP_ADDABLES.get(1))) {
			ship = new RocketShipDecorator(ship);
		} else if (addableChoice.equalsIgnoreCase("3") && !ship.containsAddable(SHIP_ADDABLES.get(2))) {
			ship = new TorpedoShipDecorator(ship);
		} else {
			System.out.println("The addable does not exist.");
		}
	}

	private void addAddableToPlane(Plane plane, BufferedReader br) throws IOException {
		printPlaneAddablesChoices(plane);
		String addableChoice = br.readLine();
		if (addableChoice.equalsIgnoreCase("1") && !plane.containsAddable(PLANE_ADDABLES.get(0))) {
			plane = new BombPlaneDecorator(plane);
		} else if (addableChoice.equalsIgnoreCase("2") && !plane.containsAddable(PLANE_ADDABLES.get(1))) {
			plane = new MachineGunPlaneDecorator(plane);
		} else if (addableChoice.equalsIgnoreCase("3") && !plane.containsAddable(PLANE_ADDABLES.get(2))) {
			plane = new MissilePlaneDecorator(plane);
		} else if (addableChoice.equalsIgnoreCase("4") && !plane.containsAddable(PLANE_ADDABLES.get(3))) {
			plane = new RocketPlaneDecorator(plane);
		} else {
			System.out.println("The addable does not exist.");
		}
	}

	private void addShipsToPlayer(BufferedReader br, Player player) throws IOException {
		printChooseShipType();
		String shipTypeChoice = br.readLine();
		if (shipTypeChoice.equals("1")) {
			player.addShip(createEntity(cruiserFactory));
		} else if (shipTypeChoice.equals("2")) {
			player.addShip(createEntity(destroyerFactory));
		} else if (shipTypeChoice.equals("3")) {
			player.addShip(createEntity(frigateFactory));
		} else {
			System.out.println("Please choose a plane from the list.");
		}
	}

	private void addPlaneToPlayer(BufferedReader br, Player player) throws IOException {
		printChoosePlaneType();
		String planeTypeChoice = br.readLine();
		if (planeTypeChoice.equals("1")) {
			player.addPlane(createEntity(bomberFactory));
		} else if (planeTypeChoice.equals("2")) {
			player.addPlane(createEntity(fighterFactory));
		} else if (planeTypeChoice.equals("3")) {
			player.addPlane(createEntity(multiroleFactory));
		} else {
			System.out.println("Please choose a plane from the list.");
		}
	}

	private Player choosePlayerWithInput(BufferedReader br) throws IOException {
		printPlayerSelection();
		String playerChoice = br.readLine();
		if (playerChoice.equals("1") || playerChoice.equals("2")) {
			Player player;
			if (playerChoice.equals("1")) {
				player = firstPlayer;
			} else {
				player = secondPlayer;
			}
			return player;
		} else {
			System.out.println("Please choose either 1 or 2.");
			return null;
		}
	}

	private void printAllItems() {
		System.out.println("First player:");
		firstPlayer.printVehicles();
		System.out.println("\nSecond player");
		secondPlayer.printVehicles();
	}

	private void printPlaneAddablesChoices(Plane plane) {
		Integer count = 1;
		for (String addable : PLANE_ADDABLES) {
			if (!plane.getAddables().isEmpty()) {
				for (PlaneAddable existing : plane.getAddables()) {
					if (!addable.equalsIgnoreCase(existing.getName())) {
						System.out.println(count + ": " + addable);
					}
				}
			} else {
				System.out.println(count + ": " + addable);
			}

			count++;
		}
	}

	private void printShipAddablesChoices(Ship ship) {
		System.out.println("Choose the addable to add to the ship.");
		Integer count = 1;
		for (String addable : SHIP_ADDABLES) {
			if (!ship.getAddables().isEmpty()) {
				for (ShipAddable existing : ship.getAddables()) {
					if (!addable.equalsIgnoreCase(existing.getName())) {
						System.out.println(count + ": " + addable);
					}
				}
			} else {
				System.out.println(count + ": " + addable);
			}
			count++;
		}
	}

	private void printEngineModificationChoices(Plane plane) {
		System.out.println("Choose the engine.");
		Integer count = 1;
		for (String engineType : PLANE_ENGINES) {
			if (plane.getEngine() == null || !engineType.equalsIgnoreCase(plane.getEngine().toString())) {
				System.out.println(count + ": " + engineType);
			}
			count++;
		}
	}

	private void printNoVehicles() {
		System.out.println("There are no vehicles available for this player.");
	}

	private void printChooseShipType() {
		System.out.println("Choose the type of the ship you want to add");
		System.out.println("1. Cruiser");
		System.out.println("2. Destroyer");
		System.out.println("3. Frigate");
	}

	private void printChoosePlaneType() {
		System.out.println("Choose the type of the plane you want to add");
		System.out.println("1. Bomber");
		System.out.println("2. Fighter");
		System.out.println("3. Multirole");
	}

	private void reset() {
		firstPlayer.reset();
		secondPlayer.reset();
		printResetMessage();
	}

	private void simulate() {
		System.out.println("\nFirst Player:");
		Integer pointsOfFirstPlayer = firstPlayer.getPoints();
		System.out.println("Second Player:");
		Integer pointsOfSecondPlayer = secondPlayer.getPoints();
		System.out.println("\nPlayer 1 points: " + pointsOfFirstPlayer);
		System.out.println("\n\nPlayer 2 points: " + pointsOfSecondPlayer + "\n\n");
		if (pointsOfFirstPlayer > pointsOfSecondPlayer) {
			System.out.println("Player 1 wins by " + (pointsOfFirstPlayer - pointsOfSecondPlayer) + " points.");
		} else {
			System.out.println("Player 2 wins by " + (pointsOfSecondPlayer - pointsOfFirstPlayer) + " points.");

		}
	}

	private Entity createEntity(AbstractFactory factory) {
		return factory.create();
	}

	private void printAddingOperations() {
		System.out.println("What kind of an item would you like to add");
		System.out.println("1. Plane");
		System.out.println("2. Ship");
	}

	private void printResetMessage() {
		System.out.println("Players items are reset");
	}

	private void printWrongInputMessage() {
		System.out.println("Please choose an item from the list.");
	}

	private void printPlayerSelection() {
		System.out.println("Select the player");
		System.out.println("1. First player");
		System.out.println("2. Second player");
	}

	private void printMainMenu() {
		System.out.println("\nMain Menu");
		System.out.println("1. Player operations");
		System.out.println("2. Run the simulation");
		System.out.println("3. Reset items of players");
		System.out.println("X. Quit");
	}

	private void printPlayerOperations() {
		System.out.println("1. Add a plane or a ship to a side.");
		System.out.println("2. Add an addable to an existing vehicle.");
		System.out.println("3. Change the engine of a plane.");
		System.out.println("4. Show the battle items of sides.");
	}

	public Player getFirstPlayer() {
		return firstPlayer;
	}

	public void setFirstPlayer(Player firstPlayer) {
		this.firstPlayer = firstPlayer;
	}

	public Player getSecondPlayer() {
		return secondPlayer;
	}

	public void setSecondPlayer(Player secondPlayer) {
		this.secondPlayer = secondPlayer;
	}

}
