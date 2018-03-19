package uk.co.bty.mock.cybersource.data;

import uk.co.bty.mock.cybersource.constants.ReasonCode;
import uk.co.bty.mock.cybersource.constants.TransactionDecision;
import uk.co.bty.mock.cybersource.constants.VeresEnrolled;

import lombok.Builder;
import lombok.ToString;
import lombok.Value;

@ToString
@Value
@Builder
public class PayerAuthEnrollResponseData
{
	private TransactionDecision decision;
	private ReasonCode reasonCode;
	private VeresEnrolled enrolled;
	private String message;
}
