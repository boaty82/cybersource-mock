package uk.co.bty.mock.cybersource.service.token.impl;

import uk.co.bty.mock.cybersource.data.TokenRequestData;

public class BillingAddressPostCodeValidator extends FieldCannotBeBlankRequestDataValidator
{
	@Override
	String getValue(final TokenRequestData data)
	{
		return data.getBillPostalCode();
	}

	@Override
	String getFieldName()
	{
		return "bill_to_address_postal_code";
	}
}
