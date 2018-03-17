package uk.co.bty.mock.cybersource.service.token.converter;

import org.springframework.core.convert.converter.Converter;

import uk.co.bty.mock.cybersource.constants.CardType;
import uk.co.bty.mock.cybersource.dao.token.Token;
import uk.co.bty.mock.cybersource.data.TokenRequestData;
import uk.co.bty.mock.cybersource.data.TokenResponseData;
import uk.co.bty.mock.cybersource.rules.data.TokenRuleData;

public class TokenRuleResultEntityConverter implements Converter<TokenRuleData, Token>
{
	@Override
	public Token convert(final TokenRuleData source)
	{
		final TokenRequestData request = source.getRequest();
		final TokenResponseData response = source.getResponse();
		return Token.builder()
				.billCity(request.getBillCity())
				.billCountry(request.getBillCountry())
				.billLine1(request.getBillLine1())
				.billLine2(request.getBillLine2())
				.billPostalCode(request.getBillPostalCode())
				.billEmail(request.getBillEmail())
				.billForename(request.getBillForename())
				.billSurname(request.getBillSurname())
				.expiryDateIso(request.getExpiryDateIso())
				.cardNumber(request.getCardNumber())
				.cardType(CardType.valueOfCode(request.getCardType()))
				.currencyIso(request.getCurrencyIso())
				.customerIpAddress(request.getCustomerIpAddress())
				.localeIso(request.getLocaleIso())
				.paymentMethod(request.getPaymentMethod())
				.skipDecisionManager(request.isSkipDecisionManager())
				.transaction_type(request.getTransaction_type())
				.profile_id(request.getProfile_id())
				.decision(response.getDecision())
				.message(response.getMessage())
				.reasonCode(response.getReasonCode())
				.requestToken(response.getRequestToken())
				.build();
	}
}
