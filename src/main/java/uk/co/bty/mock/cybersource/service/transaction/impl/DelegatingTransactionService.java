package uk.co.bty.mock.cybersource.service.transaction.impl;

import java.util.List;

import uk.co.bty.mock.cybersource.schema.transaction.ReplyMessage;
import uk.co.bty.mock.cybersource.schema.transaction.RequestMessage;
import uk.co.bty.mock.cybersource.service.transaction.TransactionService;

public class DelegatingTransactionService implements TransactionService
{
	private List<TransactionService> transactionServices;

	public DelegatingTransactionService(List<TransactionService> transactionServices)
	{
		this.transactionServices = transactionServices;
	}

	@Override
	public void apply(final RequestMessage request, final ReplyMessage response)
	{
		transactionServices.forEach(t -> t.apply(request, response));
	}
}
