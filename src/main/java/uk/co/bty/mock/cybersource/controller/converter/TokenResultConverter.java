package uk.co.bty.mock.cybersource.controller.converter;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.core.convert.converter.Converter;
import org.springframework.util.CollectionUtils;

import uk.co.bty.mock.cybersource.controller.dto.SopResponseDto;
import uk.co.bty.mock.cybersource.controller.form.SopForm;
import uk.co.bty.mock.cybersource.controller.form.SopResponseForm;
import uk.co.bty.mock.cybersource.data.TokenResponseData;

public class TokenResultConverter implements Converter<SopResponseDto, SopResponseForm>
{
	@Override
	public SopResponseForm convert(final SopResponseDto source)
	{
		final SopForm sourceForm = source.getSourceForm();
		final TokenResponseData tokenResponse = source.getTokenResponse();

		return SopResponseForm.builder()
				.decision(tokenResponse.getDecision().name())
				.message(tokenResponse.getMessage())
				.payment_token(tokenResponse.getPaymentToken())
				.reason_code(tokenResponse.getReasonCode().getCode())
				.transaction_id(tokenResponse.getPaymentToken())
				.request_token(tokenResponse.getRequestToken())
				.req_card_number(tokenResponse.getMaskedCardNumber())
				.invalid_fields(concat(tokenResponse.getInvalidFields()))
				.req_access_key(sourceForm.getAccess_key())
				.req_bill_to_address_city(sourceForm.getBill_to_address_city())
				.req_bill_to_address_country(sourceForm.getBill_to_address_country())
				.req_bill_to_address_line1(sourceForm.getBill_to_address_line1())
				.req_bill_to_address_line2(sourceForm.getBill_to_address_line2())
				.req_bill_to_address_postal_code(sourceForm.getBill_to_address_postal_code())
				.req_bill_to_email(sourceForm.getBill_to_email())
				.req_bill_to_forename(sourceForm.getBill_to_forename())
				.req_bill_to_surname(sourceForm.getBill_to_surname())
				.req_card_expiry_date(sourceForm.getCard_expiry_date())
				.req_card_type(sourceForm.getCard_type())
				.req_currency(sourceForm.getCurrency())
				.req_customer_ip_address(sourceForm.getCustomer_ip_address())
				.req_locale(sourceForm.getLocale())
				.req_merchant_defined_data90(sourceForm.getMerchant_defined_data90())
				.req_override_custom_cancel_page(sourceForm.getOverride_custom_cancel_page())
				.req_override_custom_receipt_page(sourceForm.getOverride_custom_receipt_page())
				.req_payment_method(sourceForm.getPayment_method())
				.req_profile_id(sourceForm.getProfile_id())
				.req_reference_number(sourceForm.getReference_number())
				.req_skip_decision_manager(sourceForm.getSkip_decision_manager())
				.req_transaction_type(sourceForm.getTransaction_type())
				.build();
	}

	private String concat(final Set<String> values)
	{
		return CollectionUtils.isEmpty(values)
				? null
				: values.stream().collect(Collectors.joining(","));
	}
}
