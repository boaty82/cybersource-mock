package uk.co.bty.mock.cybersource.controller.converter;

import org.springframework.core.convert.converter.Converter;

import uk.co.bty.mock.cybersource.controller.form.SopForm;
import uk.co.bty.mock.cybersource.data.TokenRequestData;

public class SopFormRequestConverter implements Converter<SopForm, TokenRequestData>
{
	@Override
	public TokenRequestData convert(final SopForm source)
	{
		return TokenRequestData.builder()
				.billCity(source.getBill_to_address_city())
				.billCountry(source.getBill_to_address_country())
				.billLine1(source.getBill_to_address_line1())
				.billLine2(source.getBill_to_address_line2())
				.billPostalCode(source.getBill_to_address_postal_code())
				.billEmail(source.getBill_to_email())
				.billForename(source.getBill_to_forename())
				.billSurname(source.getBill_to_surname())
				.expiryDateIso(buildIsoYearMonth(source.getCard_expiry_date()))
				.cardNumber(source.getCard_number())
				.cardType(source.getCard_type())
				.currencyIso(source.getCurrency())
				.customerIpAddress(source.getCustomer_ip_address())
				.localeIso(source.getLocale())
				.paymentMethod(source.getPayment_method())
				.skipDecisionManager(Boolean.valueOf(source.getSkip_decision_manager()))
				.transaction_type(source.getTransaction_type())
				.profile_id(source.getProfile_id())
				.build();
	}

	private Integer buildIsoYearMonth(final String monthYear)
	{
		final String[] parts = monthYear.split("-");
		return (Integer.valueOf(parts[0]) * 100) + Integer.valueOf(parts[1]);
	}
}
