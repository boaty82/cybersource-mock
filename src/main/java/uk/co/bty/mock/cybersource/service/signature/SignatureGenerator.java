package uk.co.bty.mock.cybersource.service.signature;

import lombok.Builder;
import lombok.Getter;
import lombok.Value;

public interface SignatureGenerator
{
	/**
	 * @return field names comma separated
	 */
	String getFieldNamesSigned();

	SignedData generate(final Object data);

	@Getter
	@Value
	@Builder
	class SignedData
	{
		private String signature;
	}

	class SignatureGenerationException extends RuntimeException
	{
		public SignatureGenerationException(final Exception e)
		{
			super(e);
		}
	}

	class MissingDataForSignatureGenerationException extends RuntimeException
	{
		public MissingDataForSignatureGenerationException(final String msg)
		{
			super(msg);
		}
	}
}
