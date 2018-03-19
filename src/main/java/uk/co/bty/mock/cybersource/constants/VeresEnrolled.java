package uk.co.bty.mock.cybersource.constants;

public enum VeresEnrolled
{
	YES("Y"), NO("N"), UNKNOWN("U");

	private final String code;

	VeresEnrolled(final String code)
	{
		this.code = code;
	}

	public String getCode()
	{
		return code;
	}
}
