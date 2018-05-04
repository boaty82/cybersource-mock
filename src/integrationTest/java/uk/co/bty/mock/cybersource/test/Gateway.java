package uk.co.bty.mock.cybersource.test;

import uk.co.bty.mock.cybersource.schema.transaction.ReplyMessage;
import uk.co.bty.mock.cybersource.schema.transaction.RequestMessage;

public interface Gateway
{
	ReplyMessage getResponse(final RequestMessage requestMessage);
}
