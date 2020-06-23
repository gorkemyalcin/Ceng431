package businessLayer;

import java.util.ArrayList;
import java.util.List;

import businessLayer.enums.CurrencyType;

/**
 * A Currencies class that has a list of currency items.
 *
 */
public class Currencies {

	private List<Currency<CurrencyType>> currencyList;

	public Currencies() {
	}

	public Currencies(List<Currency<CurrencyType>> currencyList) {
		this.currencyList = currencyList;
	}

	/**
	 * @param trackLength,             an integer value that is used for creating
	 *                                 the currencies on the track.
	 * @param currencyLengthFrequency, an integer value that is used to calculate
	 *                                 how many currencies should be created for the
	 *                                 track. Initializes the currencies on the
	 *                                 track.
	 */
	public void initialize(Integer trackLength, Integer currencyLengthFrequency) {
		this.currencyList = new ArrayList<>();
		for (int i = 0; i < trackLength / currencyLengthFrequency; i++) {
			Currency<CurrencyType> currency = new Currency<>();
			currency.initialize((i + 1) * currencyLengthFrequency);
			currencyList.add(currency);
		}
	}

	/**
	 * Removes the first (smallest distance from the start point) currency
	 */
	public void removeFirstCurrency() {
		if (currencyList != null && !currencyList.isEmpty()) {
			currencyList.remove(0);
		}
	}

	/**
	 * Returns the first (smallest distance from the start point) currency
	 */
	public Currency<CurrencyType> getCurrentCurrency() {
		if (currencyList != null && !currencyList.isEmpty()) {
			return currencyList.get(0);
		}
		return null;
	}

	public List<Currency<CurrencyType>> getCurrencyList() {
		return currencyList;
	}

	public void addAdditionalCurrencies(Integer currentLocation, Integer currencyLengthFrequency) {
		for (int i = 0; i < currentLocation / currencyLengthFrequency; i++) {
			Currency<CurrencyType> currency = new Currency<>();
			currency.initialize(((i + 1) * currencyLengthFrequency) + currentLocation);
			currencyList.add(currency);
		}
	}

}
