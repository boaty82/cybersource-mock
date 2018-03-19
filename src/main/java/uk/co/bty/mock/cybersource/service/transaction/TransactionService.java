package uk.co.bty.mock.cybersource.service.transaction;

import uk.co.bty.mock.cybersource.schema.transaction.ReplyMessage;
import uk.co.bty.mock.cybersource.schema.transaction.RequestMessage;

public interface TransactionService
{
	void apply(final RequestMessage request, final ReplyMessage reply);
}
