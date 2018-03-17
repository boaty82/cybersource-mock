package uk.co.bty.mock.cybersource.dao.token;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import uk.co.bty.mock.cybersource.constants.CardType;
import uk.co.bty.mock.cybersource.constants.ReasonCode;
import uk.co.bty.mock.cybersource.constants.TransactionDecision;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Token
{
	//<editor-fold desc="Request Data">
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
	@Enumerated(EnumType.STRING)
	private CardType cardType;
	private String currencyIso;
	private String customerIpAddress;
	private String localeIso;
	private String paymentMethod;
	private boolean skipDecisionManager;
	private String transaction_type;
	private String profile_id;
	//</editor-fold>

	//<editor-fold desc="Response Data">
	@Id
	@GeneratedValue
	private int paymentToken;
	@Enumerated(EnumType.STRING)
	private TransactionDecision decision;
	private String message;
	@Enumerated(EnumType.STRING)
	private ReasonCode reasonCode;
	private String requestToken;
	//</editor-fold>
}
