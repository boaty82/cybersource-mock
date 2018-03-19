package uk.co.bty.mock.cybersource.service.transaction.converter;

import org.springframework.beans.factory.annotation.Required;

import uk.co.bty.mock.cybersource.Populator;
import uk.co.bty.mock.cybersource.constants.VeresEnrolled;
import uk.co.bty.mock.cybersource.dao.payerauth.PayerAuthEnroll;
import uk.co.bty.mock.cybersource.schema.transaction.PayerAuthEnrollReply;
import uk.co.bty.mock.cybersource.schema.transaction.ReplyMessage;

public class PayerAuthEnrolmentResultPopulator implements Populator<PayerAuthEnroll, ReplyMessage>
{
	private static final String DEFAULT_PAREQ = "eNpVUttugkAQfd+vIDbpI8tFCdhxE61NSmKtFU2TvlGcyFYEXZaifn13EVo7T3PmcnbmzMIqFYjTCJNKIIMXLMt4iwbfjHoT3z2H8iP8jGRZ+lH/FUOrx2AxXuKRwTeKkhc5s03LdIB2kCgKkaRxLhnEyXESzlnfCxzbA9pCAnsU4ZTZNwb0GiOQx3tkc6yNWVHsjCXKmGeK2pjxPZcItMkTSIoql+LMfEcRd4BAJTKWSnkoh5TWdW3mWGeKx0wKs9oB1WkC9G/CRaW9Ui1+4hv29rW94Dqz3ncZX7vLS3JJV/OnIItW4xFQXUFgE0tkjmX7lm0Fhm0N3cHQDoA2cQLxXs/C7u9sz33wzEBl2hCBg35sfEWeztwG1EqVEJgn3U4dIoCnQ5GjqlEy//pqjb/hH5+12IlsVA18ZVrtBjftXInjuNag6eeNUlT30PaWtD278v59hx+kb686";

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
