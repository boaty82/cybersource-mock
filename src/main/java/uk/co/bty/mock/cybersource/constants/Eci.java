package uk.co.bty.mock.cybersource.constants;

public enum Eci
{
	SUCCESS("05"),
	ATTEMPTED("06"),
	FAILED("07"),
	MASTERCARD_MERCH_LIABLE("01"),
	MASTERCARD_CARD_ISSUER_LIABLE("02");

	private final String code;

	Eci(final String code)
	{
		this.code = code;
	}

	public String getCode()
	{
		return code;
	}
}
