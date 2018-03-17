package uk.co.bty.mock.cybersource.service.token.converter;

import org.springframework.core.convert.converter.Converter;

import uk.co.bty.mock.cybersource.data.TokenRequestData;
import uk.co.bty.mock.cybersource.rules.data.TokenRuleData;

public class TokenRuleRequestConverter implements Converter<TokenRequestData, TokenRuleData>
{
	@Override
	public TokenRuleData convert(final TokenRequestData source)
	{
		final TokenRuleData ruleData = new TokenRuleData();
		ruleData.setProfileId(source.getProfile_id());
		ruleData.setRequest(source);
		return ruleData;
	}
}
