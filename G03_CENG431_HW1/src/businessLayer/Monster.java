package businessLayer;

import java.util.Random;

/**
 * A monster class that is only used for attacking and checking if it is time to
 * attack by the information given to this class.
 */
public class Monster implements IAttackable {

	public Monster() {
	}

	/**
	 * @param playerLocation, players location.
	 * @param monsterAttackLengthFrequency, how often does the monster attack.
	 * @return true if it is time to attack, false otherwise
	 */
	public boolean checkIfItIsTimeToAttack(Integer playerLocation, Integer monsterAttackLengthFrequency) {
		return playerLocation % monsterAttackLengthFrequency == 0;
	}

	
	/**
	 * @param attackProbability, Double value for monster's attack being successful.
	 * Returns true if monster successfully attacks, false otherwise.
	 */
	public boolean attack(Double attackProbability) {
		return new Random().nextDouble() < attackProbability;
	}
}
