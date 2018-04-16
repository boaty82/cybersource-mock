package uk.co.bty.mock.cybersource.service.transaction.converter;

import org.springframework.core.convert.converter.Converter;

import uk.co.bty.mock.cybersource.dao.payerauth.PayerAuthEnroll;
import uk.co.bty.mock.cybersource.dao.payerauth.PayerAuthValidation;
import uk.co.bty.mock.cybersource.dao.token.Token;
import uk.co.bty.mock.cybersource.data.PayerAuthValidationRequestData;
import uk.co.bty.mock.cybersource.data.PayerAuthValidationResponseData;
import uk.co.bty.mock.cybersource.rules.data.PayerAuthValidationRuleData;

public class PayerAuthValidationRuleResultConverter implements Converter<PayerAuthValidationRuleData, PayerAuthValidation>
{
	@Override
	public PayerAuthValidation convert(final PayerAuthValidationRuleData source)
	{
		final PayerAuthValidationRequestData request = source.getRequest();
		final Token token = request.getToken();
		final PayerAuthEnroll payerAuthEnroll = request.getEnroll();
		final PayerAuthValidationResponseData ruleResponse = source.getResponse();

		return PayerAuthValidation.builder()
				.pareq(payerAuthEnroll.getPareq())
				.xid(payerAuthEnroll.getXid())
				.payerAuthValidateResult(payerAuthEnroll.getSelectedResult())
				.paymentToken(token.getPaymentToken())
				.reasonCode(ruleResponse.getReasonCode())
				.message(ruleResponse.getMessage())
				.paresStatus(ruleResponse.getParesStatus())
				.commerceIndicator(ruleResponse.getCommerceIndicator())
				.eci(ruleResponse.getEci())
				.build();
	}
}
