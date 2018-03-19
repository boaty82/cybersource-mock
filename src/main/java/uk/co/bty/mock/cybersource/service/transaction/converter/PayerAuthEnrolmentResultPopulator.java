package uk.co.bty.mock.cybersource.service.transaction.converter;

import org.springframework.beans.factory.annotation.Required;

import uk.co.bty.mock.cybersource.Populator;
import uk.co.bty.mock.cybersource.constants.VeresEnrolled;
import uk.co.bty.mock.cybersource.dao.payerauth.PayerAuthEnroll;
import uk.co.bty.mock.cybersource.schema.transaction.PayerAuthEnrollReply;
import uk.co.bty.mock.cybersource.schema.transaction.ReplyMessage;

public class PayerAuthEnrolmentResultPopulator implements Populator<PayerAuthEnroll, ReplyMessage>
{
	private String acsUrl;

	@Override
	public void populate(final PayerAuthEnroll source, final ReplyMessage target)
	{
		target.setDecision(source.getDecision().name());
		target.setReasonCode(source.getReasonCode().getBigInt());
		target.setPayerAuthEnrollReply(PayerAuthEnrollReply.builder()
				.veresEnrolled(source.getEnrolled().getCode())
				.reasonCode(source.getReasonCode().getBigInt())
				.paReq(source.getPareq())
				.acsURL(acsUrl)
				.xid(String.valueOf(source.getXid()))
				.authenticationPath(VeresEnrolled.YES.equals(source.getEnrolled()) ? "ENROLLED" : "NOREDIRECT")
				.eci(null)
				.ucafCollectionIndicator(null)
				.commerceIndicator(null)
				.build());
	}

	@Required
	public void setAcsUrl(final String acsUrl)
	{
		this.acsUrl = acsUrl;
	}
}
