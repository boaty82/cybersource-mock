package uk.co.bty.mock.cybersource.data;

import java.util.Set;

import uk.co.bty.mock.cybersource.constants.ReasonCode;
import uk.co.bty.mock.cybersource.constants.TransactionDecision;

import lombok.Builder;
import lombok.ToString;
import lombok.Value;

@ToString
@Value
@Builder
public class TokenResponseData
{
	private TransactionDecision decision;
	private String message;
	private String paymentToken;
	private ReasonCode reasonCode;
	private String requestToken;
	private String maskedCardNumber;
	private Set<String> invalidFields;
}
