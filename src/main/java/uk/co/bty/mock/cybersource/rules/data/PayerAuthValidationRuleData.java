package uk.co.bty.mock.cybersource.rules.data;

import uk.co.bty.mock.cybersource.data.PayerAuthValidationRequestData;
import uk.co.bty.mock.cybersource.data.PayerAuthValidationResponseData;
import uk.co.bty.mock.cybersource.rules.RuleType;

public class PayerAuthValidationRuleData extends RuleData<PayerAuthValidationRequestData, PayerAuthValidationResponseData>
{
	public PayerAuthValidationRuleData()
	{
		super(RuleType.PAYER_AUTH_VALIDATION);
	}
}
