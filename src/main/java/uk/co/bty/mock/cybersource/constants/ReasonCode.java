package uk.co.bty.mock.cybersource.constants;

import java.math.BigInteger;

public enum ReasonCode
{
	ACCEPT("100"),
	MISSING_DATA("101"),
	INVALID_DATA("102"),
	NON_UNIQUE_MERCHANT_REFERENCE_CODE("104"),
	PARTIAL_APPROVAL("110"),
	SYSTEM_FAILURE("150"),
	SERVER_TIMEOUT("151"),
	SERVICE_TIMEOUT("152"),
	AVS_FAILURE("200"),
	BANK_HAS_QUESTIONS("201"),
	EXPIRED_CARD("202"),
	DECLINED("203"),
	INSUFFICIENT_FUNDS("204"),
	STOLEN_OR_LOST("205"),
	BANK_UNAVAILABLE("207"),
	INACTIVE_CARD("208"),
	CVN_MISMATCH("209"),
	REACHED_CREDIT_LIMIT("210"),
	INVALID_CVN("211"),
	NEGATIVE_CUSTOMER("221"),
	ACCOUNT_FROZEN("222"),
	PAYPAL_REJECT("223"),
	CVN_DECLINED("230"),
	INVALID_ACCOUNT_NUMBER("231"),
	CARD_TYPE_NOT_ACCEPTED("232"),
	PROCESSOR_DECLINED("233"),
	MERCHANT_CONFIG_ERROR("234"),
	CAPTURE_EXCEEDS_AUTHORIZED_AMOUNT("235"),
	PROCESSOR_FAILURE("236"),
	AUTHORIZATION_ALREADY_REVERSED("237"),
	TRANSACTION_ALREADY_COMPLETED("238"),
	TRANSACTION_AMOUNT_MISMATCH("239"),
	INVALID_CARD_TYPE("240"),
	INVALID_REQUEST_ID("241"),
	CAPTURE_NOT_AUTHORIZED("242"),
	TRANSACTION_ALREADY_SETTLED("243"),
	CANNOT_VOID("246"),
	ALREADY_VOIDED("247"),
	PROCESSOR_TIMEOUT("250"),
	STAND_ALONE_CREDITS_NOT_ALLOWED("254"),
	PAYMENT_SERVICE_FAILURE("387"),
	FRAUD_SCORE_EXCEEDS_THRESHOLD("400"),
	ENROLLED_FOR_PAYER_AUTHENTICATION("475"),
	PAYER_NOT_AUTHENTICATED("476"),
	DECISION_MANAGER_REVIEW("480"),
	DECISION_MANAGER_REJECT("481"),
	DECLINED_BY_DECISION_MANAGER("520"),
	UNKNOWN_REASON_CODE("-1");

	private final String code;

	ReasonCode(final String code)
	{
		this.code = code;
	}

	public String getCode()
	{
		return code;
	}

	public BigInteger getBigInt() {
		return new BigInteger(code);
	}
}
