package uk.co.bty.mock.cybersource.rules.data;

import lombok.Data;
import lombok.EqualsAndHashCode;
import uk.co.bty.mock.cybersource.data.AuthTxnRequestData;
import uk.co.bty.mock.cybersource.data.AuthTxnResponseData;
import uk.co.bty.mock.cybersource.rules.RuleType;

@Data
@EqualsAndHashCode(callSuper=true)
public class AuthTxnRuleData extends RuleData<AuthTxnRequestData, AuthTxnResponseData>
{
	private String responseId;
	
	public AuthTxnRuleData()
	{
		super(RuleType.AUTH_TXN);
	}
}
