package uk.co.bty.mock.cybersource.service.token;

import uk.co.bty.mock.cybersource.data.TokenRequestData;
import uk.co.bty.mock.cybersource.data.TokenResponseData;

public interface TokenisationService
{
	TokenResponseData tokenise(final TokenRequestData request);
}
