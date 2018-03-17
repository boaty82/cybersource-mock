package uk.co.bty.mock.cybersource.stepdefs.token;

import lombok.Data;

@Data
public class CardData
{
	private String cardType;
	private String cardNumber;
	private String expMonth;
	private String expYear;
	private String forename;
	private String surname;

}
