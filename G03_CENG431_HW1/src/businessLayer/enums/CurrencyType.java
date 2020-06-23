package businessLayer.enums;

public enum CurrencyType {
	MAGNETIC_COIN(200), COIN(100), DIAMOND(400);

	private Integer currencyValue;

	CurrencyType(Integer currencyValue) {
		this.currencyValue = currencyValue;
	}

	public Integer getCurrencyValue() {
		return currencyValue;
	}
}
