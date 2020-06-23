package businessLayer;

import java.util.Random;

import businessLayer.enums.CurrencyType;

public class Currency<T> extends Event<T> {

	private CurrencyType currencyType;

	public Currency() {
	}

	public Currency(T currencyType, Integer currencyLocation) {
		super(currencyLocation, currencyType);
	}

	/**
	 * @param location, an Integer value that is used for creating the currency at that location.
	 * Initializes the current currency by the location of the currency and the type of the currency.
	 */
	public void initialize(Integer location) {
		super.setEventLocation(location);

		CurrencyType type;
		Random random = new Random();
		Double currencyTypeProbability = random.nextDouble();
		if (currencyTypeProbability < 0.33) {
			type = CurrencyType.COIN;
		} else if (currencyTypeProbability < 0.67) {
			type = CurrencyType.MAGNETIC_COIN;
		} else {
			type = CurrencyType.DIAMOND;
		}
		this.currencyType = type;
	}

	public CurrencyType getCurrencyType() {
		return currencyType;
	}
}
