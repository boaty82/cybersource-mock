package uk.co.bty.mock.cybersource.service.token.impl;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.core.convert.converter.Converter;

import uk.co.bty.mock.cybersource.dao.token.Token;
import uk.co.bty.mock.cybersource.data.TokenRequestData;
import uk.co.bty.mock.cybersource.data.TokenResponseData;
import uk.co.bty.mock.cybersource.rules.RuleEngine;
import uk.co.bty.mock.cybersource.rules.data.TokenRuleData;
import uk.co.bty.mock.cybersource.service.ResultSavingService;
import uk.co.bty.mock.cybersource.service.token.TokenisationService;

public class RuleAwareTokenisationService implements TokenisationService
{
	private RuleEngine ruleEngine;
	private Converter<TokenRequestData, TokenRuleData> ruleRequestConverter;
	private ResultSavingService<TokenRuleData, Token, Integer> resultSavingService;
	private Converter<Token, TokenResponseData> entityResponseConverter;

	@Override
	public TokenResponseData tokenise(final TokenRequestData request)
	{
		final TokenRuleData ruleData = ruleRequestConverter.convert(request);
		ruleEngine.apply(ruleData);
		final Token savedToken = resultSavingService.save(ruleData);
		return entityResponseConverter.convert(savedToken);
	}

	@Required
	public void setRuleEngine(RuleEngine ruleEngine)
	{
		this.ruleEngine = ruleEngine;
	}

	@Required
	public void setRuleRequestConverter(final Converter<TokenRequestData, TokenRuleData> ruleRequestConverter)
	{
		this.ruleRequestConverter = ruleRequestConverter;
	}

	@Required
	public void setResultSavingService(final ResultSavingService<TokenRuleData, Token, Integer> resultSavingService)
	{
		this.resultSavingService = resultSavingService;
	}

	@Required
	public void setEntityResponseConverter(final Converter<Token, TokenResponseData> entityResponseConverter)
	{
		this.entityResponseConverter = entityResponseConverter;
	}
}
