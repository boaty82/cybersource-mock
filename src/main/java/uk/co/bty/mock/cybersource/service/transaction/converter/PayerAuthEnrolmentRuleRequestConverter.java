package uk.co.bty.mock.cybersource.service.transaction.converter;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.core.convert.converter.Converter;

import uk.co.bty.mock.cybersource.dao.token.Token;
import uk.co.bty.mock.cybersource.dao.token.TokenRepository;
import uk.co.bty.mock.cybersource.data.PayerAuthEnrollRequestData;
import uk.co.bty.mock.cybersource.rules.data.PayerAuthEnrollRuleData;
import uk.co.bty.mock.cybersource.schema.transaction.RecurringSubscriptionInfo;
import uk.co.bty.mock.cybersource.schema.transaction.RequestMessage;

public class PayerAuthEnrolmentRuleRequestConverter implements Converter<RequestMessage, PayerAuthEnrollRuleData>
{
	private TokenRepository tokenRepository;

	@Override
	public PayerAuthEnrollRuleData convert(final RequestMessage source)
	{
		final Token token = Optional.ofNullable(source.getRecurringSubscriptionInfo())
				.map(RecurringSubscriptionInfo::getSubscriptionID)
				.map(Integer::valueOf)
				.map(tokenRepository::findOne)
				.orElseThrow(() -> new IllegalArgumentException("Unable to locate saved token request"));

		final PayerAuthEnrollRuleData ruleData = new PayerAuthEnrollRuleData();
		ruleData.setRequest(PayerAuthEnrollRequestData.builder()
				.token(token)
				.request(source)
				.build());
		ruleData.setProfileId(source.getMerchantID());

		return ruleData;
	}

	@Required
	public void setTokenRepository(final TokenRepository tokenRepository)
	{
		this.tokenRepository = tokenRepository;
	}
}
