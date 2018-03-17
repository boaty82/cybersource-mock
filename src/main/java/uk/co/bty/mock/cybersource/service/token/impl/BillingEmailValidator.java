package uk.co.bty.mock.cybersource.service.token.impl;

import uk.co.bty.mock.cybersource.data.TokenRequestData;

public class BillingEmailValidator extends FieldCannotBeBlankRequestDataValidator
{
	@Override
	String getValue(final TokenRequestData data)
	{
		return data.getBillEmail();
	}

	@Override
	String getFieldName()
	{
		return "bill_to_email";
	}
}
