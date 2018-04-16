package uk.co.bty.mock.cybersource.constants;

public enum ParesStatus
{
	PROOF_AUTH_ATTEMPT_GENERATED("A"),
	FAILED_OR_CANCELLED_AUTHENTICATION("N"),
	NOT_COMPLETE("U"),
	SUCCESS("Y");

	private final String code;

	ParesStatus(final String code)
	{
		this.code = code;
	}

	public String getCode()
	{
		return code;
	}
}
