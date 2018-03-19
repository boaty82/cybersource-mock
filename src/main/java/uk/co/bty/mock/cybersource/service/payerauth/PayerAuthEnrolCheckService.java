package uk.co.bty.mock.cybersource.service.payerauth;

import uk.co.bty.mock.cybersource.constants.PayerAuthValidateResult;

public interface PayerAuthEnrolCheckService
{
	void updateWith(final String xid, final PayerAuthValidateResult selected);
}
