package uk.co.bty.mock.cybersource.service.transaction.impl;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.core.convert.converter.Converter;

import java.util.Optional;

import uk.co.bty.mock.cybersource.Populator;
import uk.co.bty.mock.cybersource.dao.txn.AuthTxn;
import uk.co.bty.mock.cybersource.rules.RuleEngine;
import uk.co.bty.mock.cybersource.rules.data.AuthTxnRuleData;
import uk.co.bty.mock.cybersource.schema.transaction.CCAuthService;
import uk.co.bty.mock.cybersource.schema.transaction.ReplyMessage;
import uk.co.bty.mock.cybersource.schema.transaction.RequestMessage;
import uk.co.bty.mock.cybersource.service.ResultSavingService;
import uk.co.bty.mock.cybersource.service.transaction.TransactionService;

import static org.slf4j.LoggerFactory.getLogger;

public class AuthorisationTransactionService implements TransactionService
{
	private static final Logger LOG = getLogger(AuthorisationTransactionService.class);

	private Converter<RequestMessage, AuthTxnRuleData> ruleRequestConverter;
	private RuleEngine ruleEngine;
	private ResultSavingService<AuthTxnRuleData, AuthTxn, String> resultSavingService;
	private Populator<AuthTxn, ReplyMessage> ruleResultPopulator;

	@Override
	public void apply(final RequestMessage request, final ReplyMessage response)
	{
		if (!Optional.ofNullable(request.getCcAuthService())
				.map(CCAuthService::getRun)
				.map(Boolean::valueOf)
				.orElse(false))
		{
			LOG.debug("I am not applicable for requestMessage [{}]", request);
			return;
		}

		LOG.debug("Happy days, I am applicable for requestMessage [{}]", request);

		final AuthTxnRuleData ruleData = ruleRequestConverter.convert(request);
		ruleEngine.apply(ruleData);

		final AuthTxn payerAuthValidation = resultSavingService.save(ruleData);
		ruleResultPopulator.populate(payerAuthValidation, response);
	}

	@Required
	public void setRuleRequestConverter(final Converter<RequestMessage, AuthTxnRuleData> ruleRequestConverter)
	{
		this.ruleRequestConverter = ruleRequestConverter;
	}

	@Required
	public void setRuleEngine(final RuleEngine ruleEngine)
	{
		this.ruleEngine = ruleEngine;
	}

	@Required
	public void setResultSavingService(final ResultSavingService<AuthTxnRuleData, AuthTxn, String> resultSavingService)
	{
		this.resultSavingService = resultSavingService;
	}

	@Required
	public void setRuleResultPopulator(final Populator<AuthTxn, ReplyMessage> ruleResultPopulator)
	{
		this.ruleResultPopulator = ruleResultPopulator;
	}
}
