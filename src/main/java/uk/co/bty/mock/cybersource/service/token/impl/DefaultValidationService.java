package uk.co.bty.mock.cybersource.service.token.impl;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Required;

import uk.co.bty.mock.cybersource.data.TokenRequestData;
import uk.co.bty.mock.cybersource.service.token.ValidationService;
import uk.co.bty.mock.cybersource.service.token.Validator;

public class DefaultValidationService implements ValidationService<TokenRequestData>
{
	private List<Validator<TokenRequestData>> validators;

	@Override
	public ResultData validate(final TokenRequestData data)
	{
		final Map<String, String> errors = getValidators().stream()
				.map(v -> v.validate(data))
				.filter(r -> !r.isValid())
				.collect(Collectors.toMap(Validator.ResultData::getFieldName, Validator.ResultData::getErrorMessage));

		return errors.isEmpty()
				? ResultData.valid()
				: ResultData.invalid(errors);
	}

	protected List<Validator<TokenRequestData>> getValidators()
	{
		return validators;
	}

	@Required
	public void setValidators(final List<Validator<TokenRequestData>> validators)
	{
		this.validators = validators;
	}
}
