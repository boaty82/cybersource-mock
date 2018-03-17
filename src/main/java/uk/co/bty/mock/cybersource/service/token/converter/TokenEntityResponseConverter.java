package uk.co.bty.mock.cybersource.service.token.converter;

import org.springframework.core.convert.converter.Converter;

import uk.co.bty.mock.cybersource.dao.token.Token;
import uk.co.bty.mock.cybersource.data.TokenResponseData;

public class TokenEntityResponseConverter implements Converter<Token, TokenResponseData>
{
	@Override
	public TokenResponseData convert(final Token source)
	{
		return TokenResponseData.builder()
			.decision(source.getDecision())
			.message(source.getMessage())
			.paymentToken(String.valueOf(source.getPaymentToken()))
			.reasonCode(source.getReasonCode())
			.requestToken(source.getRequestToken())
			.maskedCardNumber(mask(source.getCardNumber()))
			.build();
	}

	private String mask(final String cardNumber)
	{
		int breakAt = cardNumber.length() - 4;
		final String suffix = cardNumber.substring(breakAt);
		return cardNumber.substring(0, breakAt).replaceAll("[0-9]", "*") + suffix;
	}
}
