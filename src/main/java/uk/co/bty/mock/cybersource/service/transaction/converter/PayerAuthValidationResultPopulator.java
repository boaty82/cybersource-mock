package uk.co.bty.mock.cybersource.service.transaction.converter;

import uk.co.bty.mock.cybersource.Populator;
import uk.co.bty.mock.cybersource.dao.payerauth.PayerAuthValidation;
import uk.co.bty.mock.cybersource.schema.transaction.PayerAuthValidateReply;
import uk.co.bty.mock.cybersource.schema.transaction.ReplyMessage;

public class PayerAuthValidationResultPopulator implements Populator<PayerAuthValidation, ReplyMessage>
{
	@Override
	public void populate(final PayerAuthValidation source, final ReplyMessage target)
	{
		target.setPayerAuthValidateReply(PayerAuthValidateReply.builder()
				.reasonCode(source.getReasonCode().getBigInt())
				.authenticationResult(source.getPayerAuthValidateResult().getCode())
				.authenticationStatusMessage(source.getMessage())
				.cavv(source.getCavv())
				.cavvAlgorithm("1") //No docs on this!
				.commerceIndicator(source.getCommerceIndicator().getCode())
				.eci(source.getEci().getCode())
				.eciRaw(source.getEci().getCode())
				.xid(source.getXid())
				.ucafAuthenticationData(null)
				.ucafCollectionIndicator(null)
				.paresStatus(source.getParesStatus().getCode())
				.build()
		);
	}
}
