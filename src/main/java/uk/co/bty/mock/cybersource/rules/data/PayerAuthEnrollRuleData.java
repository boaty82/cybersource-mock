package uk.co.bty.mock.cybersource.rules.data;

import uk.co.bty.mock.cybersource.data.PayerAuthEnrollRequestData;
import uk.co.bty.mock.cybersource.data.PayerAuthEnrollResponseData;
import uk.co.bty.mock.cybersource.rules.RuleType;

public class PayerAuthEnrollRuleData extends RuleData<PayerAuthEnrollRequestData, PayerAuthEnrollResponseData>
{
	public PayerAuthEnrollRuleData()
	{
		super(RuleType.PAYER_AUTH_ENROLL);
	}
}
