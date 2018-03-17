package uk.co.bty.mock.cybersource.service.token.impl;

import org.slf4j.Logger;

import uk.co.bty.mock.cybersource.data.TokenRequestData;
import uk.co.bty.mock.cybersource.service.token.Validator;

import static org.slf4j.LoggerFactory.getLogger;

public class TransactionTypeValidator implements Validator<TokenRequestData>
{
	private static final Logger LOG = getLogger(TransactionTypeValidator.class);
	private static final String CREATE_TOKEN = "create_payment_token";
	private static final String FIELD_NAME = "transaction_type";

	@Override
	public ResultData validate(final TokenRequestData data)
	{
		final ResultData result = !CREATE_TOKEN.equals(data.getTransaction_type())
				? ResultData.invalid(FIELD_NAME, data.getTransaction_type() + " is not supported")
				: ResultData.valid();

		if (!result.isValid())
		{
			LOG.info("{} result {}", FIELD_NAME, result);
		}
		return result;
	}
}
