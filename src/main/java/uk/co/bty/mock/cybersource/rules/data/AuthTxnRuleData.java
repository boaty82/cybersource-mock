package uk.co.bty.mock.cybersource.rules.data;

import uk.co.bty.mock.cybersource.data.AuthTxnRequestData;
import uk.co.bty.mock.cybersource.data.AuthTxnResponseData;
import uk.co.bty.mock.cybersource.rules.RuleType;

public class AuthTxnRuleData extends RuleData<AuthTxnRequestData, AuthTxnResponseData>
{
	public AuthTxnRuleData()
	{
		super(RuleType.AUTH_TXN);
	}
}
