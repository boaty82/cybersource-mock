package uk.co.bty.mock.cybersource.constants;

public enum CvnCode
{
	SUSPICIOUS("D"),
	FAILED_DATA_VALIDATION("I"),
	MATCH("M"),
	NOT_MATCHED("N"),
	NOT_PROCESSED_UNSPECIFIED("P"),
	CVN_NOT_IN_REQUEST("S"),
	NOT_SUPPORTED_BY_BANK("U"),
	NOT_SUPPORTED_BY_CARD_ASSOC("X"),
	NOT_SUPPORTED_FOR_CARD_TYPE("1"),
	UNRECOGNIZED_RESULT_CODE("2"),
	NO_RESULT("3");

	private final String code;

	CvnCode(final String code)
	{
		this.code = code;
	}

	public String getCode()
	{
		return code;
	}
}
