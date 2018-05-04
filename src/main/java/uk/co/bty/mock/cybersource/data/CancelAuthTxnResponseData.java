package uk.co.bty.mock.cybersource.data;

import lombok.Builder;
import lombok.ToString;
import lombok.Value;
import uk.co.bty.mock.cybersource.constants.ReasonCode;
import uk.co.bty.mock.cybersource.constants.TransactionDecision;

@ToString
@Value
@Builder
public class CancelAuthTxnResponseData
{
	private TransactionDecision decision;
	private ReasonCode reasonCode;
}
