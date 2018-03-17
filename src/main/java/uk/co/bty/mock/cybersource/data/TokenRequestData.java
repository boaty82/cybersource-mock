package uk.co.bty.mock.cybersource.data;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class TokenRequestData
{
	private String billCity;
	private String billCountry;
	private String billLine1;
	private String billLine2;
	private String billPostalCode;
	private String billEmail;
	private String billForename;
	private String billSurname;
	private Integer expiryDateIso;
	private String cardNumber;
	private String cardType;
	private String currencyIso;
	private String customerIpAddress;
	private String localeIso;
	private String paymentMethod;
	private boolean skipDecisionManager;
	private String transaction_type;
	private String profile_id;
}