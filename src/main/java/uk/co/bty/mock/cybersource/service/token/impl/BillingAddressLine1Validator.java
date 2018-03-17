package uk.co.bty.mock.cybersource.service.token.impl;

import uk.co.bty.mock.cybersource.data.TokenRequestData;

public class BillingAddressLine1Validator extends FieldCannotBeBlankRequestDataValidator
{
	@Override
	String getValue(final TokenRequestData data)
	{
		return data.getBillLine1();
	}

	@Override
	String getFieldName()
	{
		return "bill_to_address_line1";
	}
}
