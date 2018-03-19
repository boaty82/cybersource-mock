package uk.co.bty.mock.cybersource.service.transaction.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.core.convert.converter.Converter;

import uk.co.bty.mock.cybersource.Populator;
import uk.co.bty.mock.cybersource.dao.payerauth.PayerAuthEnroll;
import uk.co.bty.mock.cybersource.rules.RuleEngine;
import uk.co.bty.mock.cybersource.rules.data.PayerAuthEnrollRuleData;
import uk.co.bty.mock.cybersource.schema.transaction.PayerAuthEnrollService;
import uk.co.bty.mock.cybersource.schema.transaction.ReplyMessage;
import uk.co.bty.mock.cybersource.schema.transaction.RequestMessage;
import uk.co.bty.mock.cybersource.service.ResultSavingService;
import uk.co.bty.mock.cybersource.service.transaction.TransactionService;

import static org.slf4j.LoggerFactory.getLogger;

public class PayerAuthEnrolmentTransactionService implements TransactionService
{
	private static final Logger LOG = getLogger(PayerAuthEnrolmentTransactionService.class);

	private Converter<RequestMessage, PayerAuthEnrollRuleData> ruleRequestConverter;
	private RuleEngine ruleEngine;
	private ResultSavingService<PayerAuthEnrollRuleData, PayerAuthEnroll, String> resultSavingService;
	private Populator<PayerAuthEnroll, ReplyMessage> ruleResultPopulator;

	@Override
	public void apply(final RequestMessage request, final ReplyMessage response)
	{
		if (!Optional.ofNullable(request.getPayerAuthEnrollService())
				.map(PayerAuthEnrollService::getRun)
				.map(Boolean::valueOf)
				.orElse(false))
		{
			LOG.debug("I am not applicable for requestMessage [{}]", request);
			return;
		}

		LOG.debug("Happy days, I am applicable for requestMessage [{}]", request);

		final PayerAuthEnrollRuleData ruleData = ruleRequestConverter.convert(request);
		ruleEngine.apply(ruleData);

		final PayerAuthEnroll payerAuthEnroll = resultSavingService.save(ruleData);
		ruleResultPopulator.populate(payerAuthEnroll, response);
	}

	@Required
	public void setRuleEngine(final RuleEngine ruleEngine)
	{
		this.ruleEngine = ruleEngine;
	}

	@Required
	public void setRuleRequestConverter(final Converter<RequestMessage, PayerAuthEnrollRuleData> ruleRequestConverter)
	{
		this.ruleRequestConverter = ruleRequestConverter;
	}

	@Required
	public void setResultSavingService(final ResultSavingService<PayerAuthEnrollRuleData, PayerAuthEnroll, String> resultSavingService)
	{
		this.resultSavingService = resultSavingService;
	}

	@Required
	public void setRuleResultPopulator(final Populator<PayerAuthEnroll, ReplyMessage> ruleResultPopulator)
	{
		this.ruleResultPopulator = ruleResultPopulator;
	}

}
