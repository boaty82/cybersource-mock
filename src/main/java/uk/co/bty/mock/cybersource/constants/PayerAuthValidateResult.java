package uk.co.bty.mock.cybersource.constants;

public enum PayerAuthValidateResult
{
	INVALID_PARES("-1"),
	SUCCESS("0"),
	CARD_HOLDER_NOT_PARTICIPATING("1"),
	UNABLE_TO_AUTHENTICATE("6"),
	TXN_NOT_COMPLETE("9");

	private final String code;

	PayerAuthValidateResult(final String code)
	{
		this.code = code;
	}

	public String getCode()
	{
		return code;
	}
}
