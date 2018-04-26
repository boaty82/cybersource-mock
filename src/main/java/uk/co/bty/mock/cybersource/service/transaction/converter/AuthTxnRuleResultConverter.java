package uk.co.bty.mock.cybersource.service.transaction.converter;

import org.joda.time.DateTime;
import org.springframework.core.convert.converter.Converter;

import uk.co.bty.mock.cybersource.dao.txn.AuthTxn;
import uk.co.bty.mock.cybersource.data.AuthTxnResponseData;
import uk.co.bty.mock.cybersource.rules.data.AuthTxnRuleData;

public class AuthTxnRuleResultConverter implements Converter<AuthTxnRuleData, AuthTxn>
{
	@Override
	public AuthTxn convert(final AuthTxnRuleData source)
	{
		final AuthTxnResponseData response = source.getResponse();

		return AuthTxn.builder()
				.merchantId(source.getProfileId())
				.amount(source.getRequest().getRequest().getPurchaseTotals().getGrandTotalAmount())
				.decision(response.getDecision())
				.reasonCode(response.getReasonCode())
				.avsCode(response.getAvsCode())
				.cvnCode(response.getCvnCode())
				.authCode(response.getAuthCode())
				.time(DateTime.now().toString())
				.build();
	}
}
