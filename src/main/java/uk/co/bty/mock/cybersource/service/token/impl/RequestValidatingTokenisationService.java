package uk.co.bty.mock.cybersource.service.token.impl;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Required;

import uk.co.bty.mock.cybersource.constants.ReasonCode;
import uk.co.bty.mock.cybersource.constants.TransactionDecision;
import uk.co.bty.mock.cybersource.data.TokenRequestData;
import uk.co.bty.mock.cybersource.data.TokenResponseData;
import uk.co.bty.mock.cybersource.service.token.TokenisationService;
import uk.co.bty.mock.cybersource.service.token.ValidationService;
import uk.co.bty.mock.cybersource.service.token.ValidationService.ResultData;

import static org.slf4j.LoggerFactory.getLogger;

public class RequestValidatingTokenisationService implements TokenisationService
{
	private static final Logger LOG = getLogger(RequestValidatingTokenisationService.class);

	private ValidationService<TokenRequestData> validationService;
	private TokenisationService delegateTokenisationService;


	@Override
	public TokenResponseData tokenise(final TokenRequestData request)
	{
		final ResultData validationResult = getValidationService().validate(request);

		final TokenResponseData result = validationResult.isValid()
				? getDelegateTokenisationService().tokenise(request)
				: buildErrorResponse(validationResult);

		LOG.info("Result is: {}", result);
		return result;

	}

	private TokenResponseData buildErrorResponse(final ResultData validationResult)
	{
		return TokenResponseData.builder()
				.decision(TransactionDecision.ERROR)
				.message("Request parameters are invalid or missing")
				.reasonCode(ReasonCode.INVALID_DATA)
				.maskedCardNumber("xxxx")
				.invalidFields(validationResult.getErrors().keySet())
				.build();
	}

	protected ValidationService<TokenRequestData> getValidationService()
	{
		return validationService;
	}

	@Required
	public void setValidationService(final ValidationService<TokenRequestData> validationService)
	{
		this.validationService = validationService;
	}

	protected TokenisationService getDelegateTokenisationService()
	{
		return delegateTokenisationService;
	}

	@Required
	public void setDelegateTokenisationService(final TokenisationService delegateTokenisationService)
	{
		this.delegateTokenisationService = delegateTokenisationService;
	}
}
