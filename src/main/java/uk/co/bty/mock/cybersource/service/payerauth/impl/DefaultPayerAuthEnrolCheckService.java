package uk.co.bty.mock.cybersource.service.payerauth.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Required;

import uk.co.bty.mock.cybersource.constants.PayerAuthValidateResult;
import uk.co.bty.mock.cybersource.dao.payerauth.PayerAuthEnroll;
import uk.co.bty.mock.cybersource.dao.payerauth.PayerAuthEnrollRepository;
import uk.co.bty.mock.cybersource.service.payerauth.PayerAuthEnrolCheckService;

public class DefaultPayerAuthEnrolCheckService implements PayerAuthEnrolCheckService
{
	private PayerAuthEnrollRepository payerAuthEnrollRepository;

	@Override
	public void updateWith(final String xid, final PayerAuthValidateResult selected)
	{
		final PayerAuthEnroll authData = Optional.of(payerAuthEnrollRepository.findByXid(xid))
				.orElseThrow(() -> new IllegalArgumentException("Unable to locate saved PayerAuth for: " + xid));

		authData.setSelectedResult(selected);
		payerAuthEnrollRepository.save(authData);
	}

	@Required
	public void setPayerAuthEnrollRepository(final PayerAuthEnrollRepository payerAuthEnrollRepository)
	{
		this.payerAuthEnrollRepository = payerAuthEnrollRepository;
	}
}
