package uk.co.bty.mock.cybersource.constants;

public enum PayerAuthValidateCommerceIndicator
{
	AMEX_SUCCESS("aesk"),
	AMEX_ATTEMPTED("aesk_attempted"),
	NOT_VERIFIED_SUCCESSFULLY("internet"),
	JSECURE_SUCCESS("js"),
	JSECURE_ATTEMPTED("js_attempted"),
	SPA_SUCCESS("spa"),
	SPA_FAILURE("spa_failure"),
	VBV_SUCCESS("vbv"),
	VBV_ATTEMPTED("vbv_attempted"),
	VBV_FAILURE("vbv_failure");

	private final String code;

	PayerAuthValidateCommerceIndicator(final String code)
	{
		this.code = code;
	}

	public String getCode()
	{
		return code;
	}
}
