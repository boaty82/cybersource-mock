package uk.co.bty.mock.cybersource.data;

import uk.co.bty.mock.cybersource.constants.AvsCode;
import uk.co.bty.mock.cybersource.constants.CvnCode;
import uk.co.bty.mock.cybersource.constants.ReasonCode;
import uk.co.bty.mock.cybersource.constants.TransactionDecision;

import lombok.Builder;
import lombok.ToString;
import lombok.Value;

@ToString
@Value
@Builder
public class AuthTxnResponseData
{
	private TransactionDecision decision;
	private ReasonCode reasonCode;
	private AvsCode avsCode;
	private CvnCode cvnCode;
	private String authCode;
}
