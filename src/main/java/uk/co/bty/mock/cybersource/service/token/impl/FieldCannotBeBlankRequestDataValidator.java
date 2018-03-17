package uk.co.bty.mock.cybersource.service.token.impl;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;

import uk.co.bty.mock.cybersource.data.TokenRequestData;
import uk.co.bty.mock.cybersource.service.token.Validator;

import static org.slf4j.LoggerFactory.getLogger;

public abstract class FieldCannotBeBlankRequestDataValidator implements Validator<TokenRequestData>
{
	private static final Logger LOG = getLogger(FieldCannotBeBlankRequestDataValidator.class);

	@Override
	public ResultData validate(final TokenRequestData data)
	{
		final ResultData result = StringUtils.isBlank(getValue(data))
				? ResultData.invalid(getFieldName(), "Cannot be blank")
				: ResultData.valid();

		if (!result.isValid())
		{
			LOG.info("{} result {}", getFieldName(), result);
		}
		return result;
	}

	abstract String getValue(final TokenRequestData data);

	abstract String getFieldName();
}
