package uk.co.bty.mock.cybersource.stepdefs.token;

public enum CardType
{
	VISA("001"),
	MASTER("002"),
	AMEX("003"),
	DINERS("005"),
	MAESTRO("042"),
	CARTE_BLEUE("036");

	private final String code;

	CardType(String code)
	{
		this.code = code;
	}

	public String getCode()
	{
		return code;
	}
}

