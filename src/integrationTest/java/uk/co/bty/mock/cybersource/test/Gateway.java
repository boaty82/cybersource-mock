package uk.co.bty.mock.cybersource.test;

import uk.co.bty.mock.cybersource.schema.transaction.ReplyMessage;
import uk.co.bty.mock.cybersource.schema.transaction.RequestMessage;

public interface Gateway
{
	ReplyMessage checkCardPayerAuthEnrollment(final RequestMessage requestMessage);

	ReplyMessage checkCardPayerAuthValidation(final RequestMessage requestMessage);

	ReplyMessage transactionAuthorisation(final RequestMessage requestMessage);
}
