package uk.co.bty.mock.cybersource.service.transaction.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.core.convert.converter.Converter;

import uk.co.bty.mock.cybersource.Populator;
import uk.co.bty.mock.cybersource.dao.payerauth.PayerAuthValidation;
import uk.co.bty.mock.cybersource.rules.RuleEngine;
import uk.co.bty.mock.cybersource.rules.data.PayerAuthValidationRuleData;
import uk.co.bty.mock.cybersource.schema.transaction.PayerAuthValidateService;
import uk.co.bty.mock.cybersource.schema.transaction.ReplyMessage;
import uk.co.bty.mock.cybersource.schema.transaction.RequestMessage;
import uk.co.bty.mock.cybersource.service.ResultSavingService;
import uk.co.bty.mock.cybersource.service.transaction.TransactionService;

import static org.slf4j.LoggerFactory.getLogger;

public class PayerAuthValidationTransactionService implements TransactionService
{
	private static final Logger LOG = getLogger(PayerAuthValidationTransactionService.class);

	private Converter<RequestMessage, PayerAuthValidationRuleData> ruleRequestConverter;
	private RuleEngine ruleEngine;
	private ResultSavingService<PayerAuthValidationRuleData, PayerAuthValidation, String> resultSavingService;
	private Populator<PayerAuthValidation, ReplyMessage> ruleResultPopulator;

	@Override
	public void apply(final RequestMessage request, final ReplyMessage response)
	{
		if (!Optional.ofNullable(request.getPayerAuthValidateService())
				.map(PayerAuthValidateService::getRun)
				.map(Boolean::valueOf)
				.orElse(false))
		{
			LOG.debug("I am not applicable for requestMessage [{}]", request);
			return;
		}

		LOG.debug("Happy days, I am applicable for requestMessage [{}]", request);

		final PayerAuthValidationRuleData ruleData = ruleRequestConverter.convert(request);
		ruleEngine.apply(ruleData);

		final PayerAuthValidation payerAuthValidation = resultSavingService.save(ruleData);
		ruleResultPopulator.populate(payerAuthValidation, response);
	}

	@Required
	public void setRuleRequestConverter(final Converter<RequestMessage, PayerAuthValidationRuleData> ruleRequestConverter)
	{
		this.ruleRequestConverter = ruleRequestConverter;
	}

	@Required
	public void setRuleEngine(final RuleEngine ruleEngine)
	{
		this.ruleEngine = ruleEngine;
	}

	@Required
	public void setResultSavingService(final ResultSavingService<PayerAuthValidationRuleData, PayerAuthValidation, String> resultSavingService)
	{
		this.resultSavingService = resultSavingService;
	}

	@Required
	public void setRuleResultPopulator(final Populator<PayerAuthValidation, ReplyMessage> ruleResultPopulator)
	{
		this.ruleResultPopulator = ruleResultPopulator;
	}
}
