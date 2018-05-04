package uk.co.bty.mock.cybersource.service.transaction.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.core.convert.converter.Converter;

import uk.co.bty.mock.cybersource.Populator;
import uk.co.bty.mock.cybersource.dao.txn.CancelAuthTxn;
import uk.co.bty.mock.cybersource.rules.RuleEngine;
import uk.co.bty.mock.cybersource.rules.data.CancelAuthTxnRuleData;
import uk.co.bty.mock.cybersource.schema.transaction.CCAuthReversalService;
import uk.co.bty.mock.cybersource.schema.transaction.ReplyMessage;
import uk.co.bty.mock.cybersource.schema.transaction.RequestMessage;
import uk.co.bty.mock.cybersource.service.ResultSavingService;
import uk.co.bty.mock.cybersource.service.transaction.TransactionService;

import static org.slf4j.LoggerFactory.getLogger;

public class CancelAuthorisationTransactionService implements TransactionService
{
	private static final Logger LOG = getLogger(CancelAuthorisationTransactionService.class);

	private Converter<RequestMessage, CancelAuthTxnRuleData> ruleRequestConverter;
	private RuleEngine ruleEngine;
	private ResultSavingService<CancelAuthTxnRuleData, CancelAuthTxn, String> resultSavingService;
	private Populator<CancelAuthTxn, ReplyMessage> ruleResultPopulator;

	@Override
	public void apply(final RequestMessage request, final ReplyMessage response)
	{
		if (!Optional.ofNullable(request.getCcAuthReversalService())
				.map(CCAuthReversalService::getRun)
				.map(Boolean::valueOf)
				.orElse(false))
		{
			LOG.debug("I am not applicable for requestMessage [{}]", request);
			return;
		}

		LOG.debug("Happy days, I am applicable for requestMessage [{}]", request);

		final CancelAuthTxnRuleData ruleData = ruleRequestConverter.convert(request);
		ruleData.setResponseId(response.getRequestID());
		ruleEngine.apply(ruleData);

		final CancelAuthTxn cancelAuthTxn = resultSavingService.save(ruleData);
		ruleResultPopulator.populate(cancelAuthTxn, response);
	}

	@Required
	public void setRuleRequestConverter(final Converter<RequestMessage, CancelAuthTxnRuleData> ruleRequestConverter)
	{
		this.ruleRequestConverter = ruleRequestConverter;
	}

	@Required
	public void setRuleEngine(final RuleEngine ruleEngine)
	{
		this.ruleEngine = ruleEngine;
	}

	@Required
	public void setResultSavingService(final ResultSavingService<CancelAuthTxnRuleData, CancelAuthTxn, String> resultSavingService)
	{
		this.resultSavingService = resultSavingService;
	}

	@Required
	public void setRuleResultPopulator(final Populator<CancelAuthTxn, ReplyMessage> ruleResultPopulator)
	{
		this.ruleResultPopulator = ruleResultPopulator;
	}
}
