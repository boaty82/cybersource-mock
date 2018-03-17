package uk.co.bty.mock.cybersource.rules.data;

import uk.co.bty.mock.cybersource.data.TokenRequestData;
import uk.co.bty.mock.cybersource.data.TokenResponseData;
import uk.co.bty.mock.cybersource.rules.RuleType;

public class TokenRuleData extends RuleData<TokenRequestData, TokenResponseData>
{
	public TokenRuleData()
	{
		super(RuleType.TOKEN);
	}
}
