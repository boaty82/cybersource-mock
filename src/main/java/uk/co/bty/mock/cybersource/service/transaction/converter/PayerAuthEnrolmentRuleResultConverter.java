package uk.co.bty.mock.cybersource.service.transaction.converter;

import org.springframework.core.convert.converter.Converter;

import uk.co.bty.mock.cybersource.dao.payerauth.PayerAuthEnroll;
import uk.co.bty.mock.cybersource.dao.token.Token;
import uk.co.bty.mock.cybersource.data.PayerAuthEnrollResponseData;
import uk.co.bty.mock.cybersource.rules.data.PayerAuthEnrollRuleData;

public class PayerAuthEnrolmentRuleResultConverter implements Converter<PayerAuthEnrollRuleData, PayerAuthEnroll>
{
	@Override
	public PayerAuthEnroll convert(final PayerAuthEnrollRuleData source)
	{
		final Token token = source.getRequest().getToken();
		final PayerAuthEnrollResponseData result = source.getResponse();
		final PayerAuthEnroll target = new PayerAuthEnroll();
		target.setDecision(result.getDecision());
		target.setEnrolled(result.getEnrolled());
		target.setReasonCode(result.getReasonCode());
		target.setMessage(result.getMessage());
		target.setPaymentToken(token.getPaymentToken());
		return target;
	}
}
