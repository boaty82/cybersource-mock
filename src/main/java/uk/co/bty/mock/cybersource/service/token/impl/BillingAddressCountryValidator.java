package uk.co.bty.mock.cybersource.service.token.impl;

import uk.co.bty.mock.cybersource.data.TokenRequestData;

public class BillingAddressCountryValidator extends FieldCannotBeBlankRequestDataValidator
{
	@Override
	String getValue(final TokenRequestData data)
	{
		return data.getBillCountry();
	}

	@Override
	String getFieldName()
	{
		return "bill_to_address_country";
	}
}
