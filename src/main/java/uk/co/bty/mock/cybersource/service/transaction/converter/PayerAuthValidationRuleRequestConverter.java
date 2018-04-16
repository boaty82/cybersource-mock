package uk.co.bty.mock.cybersource.service.transaction.converter;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.core.convert.converter.Converter;

import uk.co.bty.mock.cybersource.dao.payerauth.PayerAuthEnroll;
import uk.co.bty.mock.cybersource.dao.payerauth.PayerAuthEnrollRepository;
import uk.co.bty.mock.cybersource.dao.token.Token;
import uk.co.bty.mock.cybersource.dao.token.TokenRepository;
import uk.co.bty.mock.cybersource.data.PayerAuthValidationRequestData;
import uk.co.bty.mock.cybersource.rules.data.PayerAuthValidationRuleData;
import uk.co.bty.mock.cybersource.schema.transaction.PayerAuthValidateService;
import uk.co.bty.mock.cybersource.schema.transaction.RecurringSubscriptionInfo;
import uk.co.bty.mock.cybersource.schema.transaction.RequestMessage;

public class PayerAuthValidationRuleRequestConverter implements Converter<RequestMessage, PayerAuthValidationRuleData>
{
	private PayerAuthEnrollRepository payerAuthEnrollRepository;
	private TokenRepository tokenRepository;

	@Override
	public PayerAuthValidationRuleData convert(final RequestMessage source)
	{
		final PayerAuthEnroll payerAuth = Optional.of(source.getPayerAuthValidateService())
				.map(PayerAuthValidateService::getSignedPARes)
				.map(payerAuthEnrollRepository::findOne)
				.orElseThrow(() -> new IllegalArgumentException("Unable to locate saved Payer Auth Enroll request"));

		final Token token = Optional.ofNullable(source.getRecurringSubscriptionInfo())
				.map(RecurringSubscriptionInfo::getSubscriptionID)
				.map(Integer::valueOf)
				.map(tokenRepository::findOne)
				.orElseThrow(() -> new IllegalArgumentException("Unable to locate saved token request"));

		final PayerAuthValidationRuleData ruleData = new PayerAuthValidationRuleData();
		ruleData.setRequest(PayerAuthValidationRequestData.builder()
				.request(source)
				.enroll(payerAuth)
				.token(token)
				.build());
		ruleData.setProfileId(source.getMerchantID());
		return ruleData;
	}

	@Required
	public void setPayerAuthEnrollRepository(final PayerAuthEnrollRepository payerAuthEnrollRepository)
	{
		this.payerAuthEnrollRepository = payerAuthEnrollRepository;
	}

	@Required
	public void setTokenRepository(final TokenRepository tokenRepository)
	{
		this.tokenRepository = tokenRepository;
	}
}
