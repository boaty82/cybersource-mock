package uk.co.bty.mock.cybersource.service.transaction.converter;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.core.convert.converter.Converter;

import java.util.Optional;

import uk.co.bty.mock.cybersource.dao.token.Token;
import uk.co.bty.mock.cybersource.dao.token.TokenRepository;
import uk.co.bty.mock.cybersource.data.AuthTxnRequestData;
import uk.co.bty.mock.cybersource.rules.data.AuthTxnRuleData;
import uk.co.bty.mock.cybersource.schema.transaction.RecurringSubscriptionInfo;
import uk.co.bty.mock.cybersource.schema.transaction.RequestMessage;

public class AuthTxnRuleRequestConverter implements Converter<RequestMessage, AuthTxnRuleData>
{
	private TokenRepository tokenRepository;

	@Override
	public AuthTxnRuleData convert(final RequestMessage source)
	{
		final Token token = Optional.ofNullable(source.getRecurringSubscriptionInfo())
				.map(RecurringSubscriptionInfo::getSubscriptionID)
				.map(Integer::valueOf)
				.map(tokenRepository::findOne)
				.orElseThrow(() -> new IllegalArgumentException("Unable to locate saved token request"));

		final AuthTxnRuleData target = new AuthTxnRuleData();
		target.setProfileId(source.getMerchantID());
		target.setRequest(AuthTxnRequestData.builder()
				.token(token)
				.request(source)
				.build());
		return target;
	}

	@Required
	public void setTokenRepository(final TokenRepository tokenRepository)
	{
		this.tokenRepository = tokenRepository;
	}
}
