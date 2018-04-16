package uk.co.bty.mock.cybersource.data;

import lombok.Builder;
import lombok.ToString;
import lombok.Value;
import uk.co.bty.mock.cybersource.dao.payerauth.PayerAuthEnroll;
import uk.co.bty.mock.cybersource.dao.token.Token;
import uk.co.bty.mock.cybersource.schema.transaction.RequestMessage;

@ToString
@Value
@Builder
public class PayerAuthValidationRequestData
{
	private RequestMessage request;
	private Token token;
	private PayerAuthEnroll enroll;
}
