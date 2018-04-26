package uk.co.bty.mock.cybersource.constants;

public enum AvsCode
{
	ADDRESS_MATCH_POSTCODE_NOT_VERIFIED("B"),
	NO_MATCH("C"),
	MATCH("M"),
	MATCH_D("D"),
	ADDRESS_NOT_VERIFIED("I"),
	POSTCODE_MATCH_ADDRESS_NOT_VERIFIED("P");

	private final String code;

	AvsCode(final String code)
	{
		this.code = code;
	}

	public String getCode()
	{
		return code;
	}
}
