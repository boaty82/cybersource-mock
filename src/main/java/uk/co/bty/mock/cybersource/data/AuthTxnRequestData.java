package uk.co.bty.mock.cybersource.data;

import uk.co.bty.mock.cybersource.dao.token.Token;
import uk.co.bty.mock.cybersource.schema.transaction.RequestMessage;

import lombok.Builder;
import lombok.ToString;
import lombok.Value;

@ToString
@Value
@Builder
public class AuthTxnRequestData
{
	private RequestMessage request;
	private Token token;
}
